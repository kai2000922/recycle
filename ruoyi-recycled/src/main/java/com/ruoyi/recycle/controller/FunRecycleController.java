package com.ruoyi.recycle.controller;

import com.alipay.api.AlipayApiException;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.config.StatusConfig;
import com.ruoyi.recycle.domain.FunCoupon;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.FunUser;
import com.ruoyi.recycle.domain.express.SendOrderInfo;
import com.ruoyi.recycle.domain.request.TemplateMessageInfo;
import com.ruoyi.recycle.domain.response.CancelOrderInfoResp;
import com.ruoyi.recycle.domain.response.SendOrderInfoResp;
import com.ruoyi.recycle.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 回收订单Controller
 *
 * @author ruoyi
 * @date 2021-10-07
 */
@Controller
@RequestMapping("/recycle/recycle")
@Api("回收订单")
public class FunRecycleController extends BaseController {

    private final String prefix = "recycle/recycle";

    @Autowired
    private IFunRecycleService funRecycleService;
    @Autowired
    private IExpressService expressService;
    @Autowired
    private IFunUserService userService;
    @Autowired
    private IAliPayService aliPayService;
    @Autowired
    private IFunCouponService couponService;
    @Autowired
    private IFunOrdersService ordersService;

    //    @RequiresPermissions("recycle:recycle:view")
    @GetMapping()
    public String recycle() {
        return prefix + "/recycle";
    }

    /**
     * 查询回收订单列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunRecycle funRecycle) {
        startPage();
        //用户查询
        funRecycle.setStatu("0");
        List<FunRecycle> list = funRecycleService.selectFunRecycleList(funRecycle);
        return getDataTable(list);
    }

    @RequiresPermissions("recycle:recycle:list")
    @PostMapping("/listByAdmin")
    @ResponseBody
    public TableDataInfo listByAdmin(FunRecycle funRecycle) {
        //管理员查询
        startPage();
        List<FunRecycle> list = funRecycleService.selectFunRecycleList(funRecycle);
        return getDataTable(list);
    }

    /**
     * 导出回收订单列表
     */
    @RequiresPermissions("recycle:recycle:export")
    @Log(title = "回收订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunRecycle funRecycle) {
        List<FunRecycle> list = funRecycleService.selectFunRecycleList(funRecycle);
        ExcelUtil<FunRecycle> util = new ExcelUtil<FunRecycle>(FunRecycle.class);
        return util.exportExcel(list, "回收订单数据");
    }

    /**
     * 新增回收订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存回收订单
     */
//    @RequiresPermissions("recycle:recycle:add")
    @Log(title = "回收订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @RepeatSubmit
    @ApiOperation("添加订单")
    @ApiImplicitParam(name = "funRecycle", dataType = "FunRecycle", dataTypeClass = FunRecycle.class)
    public AjaxResult addSave(@Valid FunRecycle funRecycle, BindingResult result) {
        //判断参数是否完整
        if (result.hasErrors()) {
            return AjaxResult.error("参数错误", result.getAllErrors());
        }

        //通过 回收订单 获取 创建物流订单 实体
        SendOrderInfo orderInfo = funRecycleService.getOrder(funRecycle);
        if (orderInfo == null) {
            return AjaxResult.error("您所选择地区暂时不能回收，请您稍后再试！", funRecycle);
        }

        //储存用户信息
        userService.insertUserByRecycle(funRecycle);

        int orderNum = funRecycleService.getThisMonthOrders(funRecycle.getUser());
        if (orderNum > 3)
            return AjaxResult.error("一个月内最多回收三单哦！");

        //发送物流请求
        SendOrderInfoResp sendResp = null;
        if (funRecycle.getExpectTime().getTime() <= new Date().getTime()) {
            sendResp = FastJsonUtil.parseObject(expressService.sendOrder(orderInfo), SendOrderInfoResp.class);
            if (!sendResp.getResultCode().equals("1000")) {
                return AjaxResult.error("创建订单失败：" + sendResp.getReason());
            }
        } else {
            funRecycle.setCacelReason("等待发送订单");
        }

        //基本信息补充
        funRecycle.setCreateTime(DateUtils.getNowDate());
        funRecycle.setOrderNum(funRecycleService.getOrderByChannel(funRecycle.getChannelNum()));
        funRecycle.setOrderStatus(StatusConfig.order_wait_get);
        funRecycle.setStatu("0");
        funRecycle.setCacelReason("实时订单已创建");
        if (sendResp != null)
            funRecycle.setExpressNum(sendResp.getMailNo());

        if (funRecycleService.insertFunRecycle(funRecycle) < 1) {
            //如果添加失败，则取消该订单
            CancelOrderInfoResp cancelResp = FastJsonUtil.parseObject(expressService.cancelOrder(funRecycle), CancelOrderInfoResp.class);
            if (!cancelResp.getResultCode().equals("1000")) {
                return AjaxResult.error("取消订单失败：" + cancelResp.getReason());
            }
        }

        //添加用户回收次数
        FunUser user = null;
        try {
            user = userService.selectFunUserByUserNo(funRecycle.getUser());
            user.setRecycled(user.getRecycled() + 1);
            userService.updateFunUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户回收次数添加失败" + FastJsonUtil.toJSONString(user));
        }

        //发送优惠券
        for (FunCoupon coupon : couponService.selectFunCouponList(null)){
            if (coupon.getIsUsed().equals("Y")){
                aliPayService.sendCoupon(funRecycle.getUser(), coupon.getTemplates());
            }
        }

//        if (StringUtils.isEmpty(funRecycle.getAuthCode()))
//            funRecycle.setAuthCode("MjA4ODIyMjAxNDgxOTcwMl8xNjQwMDU3NTEwOTgzXzEzMw==");
//            return AjaxResult.success();
        //重新查询记录以获取准确的创建时间戳来发送订单消息
        funRecycle = funRecycleService.selectFunRecycleByRecycleID(funRecycle.getRecycleID());
        try {
            TemplateMessageInfo templateMessageInfo = aliPayService.RecycleToTemplateInfo(funRecycle);
            aliPayService.sendOrderMessage(templateMessageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送模板消息失败");
            logger.error(e.toString());
        }

        try {
            aliPayService.sendOrderReq(funRecycle,  "TO_SEND");
            // 更新accessToken
            funRecycleService.updateFunRecycle(funRecycle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return AjaxResult.success();
    }

    /**
     * 修改回收订单
     */
    @GetMapping("/edit/{recycleID}")
    public String edit(@PathVariable("recycleID") Long recycleID, ModelMap mmap) {
        FunRecycle funRecycle = funRecycleService.selectFunRecycleByRecycleID(recycleID);
        mmap.put("funRecycle", funRecycle);
        return prefix + "/edit";
    }

    /**
     * 修改保存回收订单
     */
    @RequiresPermissions("recycle:recycle:edit")
    @Log(title = "回收订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunRecycle funRecycle) {
        if (funRecycle.getOrderStatus().equals(StatusConfig.order_be_cancel)) {
            FunRecycle oldRecycle = funRecycleService.selectFunRecycleByRecycleID(funRecycle.getRecycleID());
            //如果订单状态为待上门，则发送物流取消该订单
            if (oldRecycle.getOrderStatus().equals(StatusConfig.order_wait_get)) {
                CancelOrderInfoResp cancelResp = FastJsonUtil.parseObject(expressService.cancelOrder(funRecycle), CancelOrderInfoResp.class);
                if (cancelResp.getResultCode() == null || !cancelResp.getResultCode().equals("1000")) {
                    logger.error("取消订单失败" + FastJsonUtil.toJSONString(cancelResp));
                    return AjaxResult.error("取消订单失败：" + cancelResp.getReason());
                }
            }
            TemplateMessageInfo templateMessageInfo = aliPayService.RecycleToTemplateInfo(funRecycle);
            aliPayService.sendOrderMessage(templateMessageInfo);
        }
        return toAjax(funRecycleService.updateFunRecycle(funRecycle));
    }

    /**
     * 删除回收订单
     */
//    @RequiresPermissions("recycle:recycle:remove")
    @Log(title = "回收订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funRecycleService.deleteFunRecycleByRecycleIDs(ids));
    }

    @PostMapping("/removeOrder")
    @ResponseBody
    public AjaxResult removeOrder(String ids) {
        FunRecycle recycle = funRecycleService.selectFunRecycleByRecycleID(Long.parseLong(ids));
        if (!Objects.equals(recycle.getOrderStatus(), "已取消"))
            return AjaxResult.error("订单未取消，不能删除！");
        //只将订单在前端不可见，不实际删除
        recycle.setStatu("1");
        return toAjax(funRecycleService.updateFunRecycle(recycle));
    }

    @Log(title = "回收订单", businessType = BusinessType.UPDATE)
    @PostMapping("/editOrder")
    @ResponseBody
    @ApiOperation("修改订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderStatus", dataType = "String"),
            @ApiImplicitParam(name = "param", dataType = "String"),
            @ApiImplicitParam(name = "orderID", dataType = "String")
    })
    public AjaxResult editOrder(@RequestParam String orderStatus, String param, Long orderID, String authCode) {
        FunRecycle recycle = funRecycleService.selectFunRecycleByRecycleID(orderID);
        if (recycle != null) {
            switch (orderStatus) {
                //取消订单
                case "-2":
                    //发送模板消息
                    try {
                        TemplateMessageInfo templateMessageInfo = aliPayService.RecycleToTemplateInfo(recycle);
                        aliPayService.sendOrderMessage(templateMessageInfo);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    //发送公益消息
                    try {
                        aliPayService.sendOrderReq(recycle,  "CANCELED");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //如果还未发送订单给物流
                    if (recycle.getExpressNum() == null || recycle.getExpressNum().equals("")) {
                        recycle.setOrderStatus(StatusConfig.order_be_cancel);
                        recycle.setUpdateTime(new Date());
                        if (funRecycleService.updateFunRecycle(recycle) > 0) {
                            recycle.setOrderStatus(StatusConfig.order_be_cancel);
                        } else
                            return AjaxResult.error();
                        return AjaxResult.success();
                    }
                    CancelOrderInfoResp cancelResp = FastJsonUtil.parseObject(expressService.cancelOrder(recycle), CancelOrderInfoResp.class);
                    if (cancelResp.getResultCode() == null || !cancelResp.getResultCode().equals("1000")) {
                        logger.error("取消订单失败" + cancelResp);
                        return AjaxResult.error("取消订单失败：" + cancelResp.getReason());
                    }
                    recycle.setOrderStatus(StatusConfig.order_be_cancel);

                    break;
                //修改订单
                case "-1":
                    //解析前端数据
                    String[] dataes = param.split("\\,");
                    if (dataes.length != 3)
                        return AjaxResult.error("参数错误", dataes);
                    recycle.setExpectTime(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", dataes[0]));
                    recycle.setExpectWeight(Long.parseLong(dataes[1]));
                    String[] data = dataes[2].split("\\;");
                    if (data.length == 6) {
                        recycle.setProv(data[0]);
                        recycle.setCity(data[1]);
                        recycle.setArea(data[2]);
                        recycle.setAddress(data[3]);
                        recycle.setName(data[4]);
                        recycle.setPhone(data[5]);
                    }
                    SendOrderInfo orderInfo = funRecycleService.getOrder(recycle);
                    if (orderInfo == null) {
                        return AjaxResult.error("您所在地区暂不能回收！", recycle);
                    }
                    if (recycle.getExpressNum() != null && !recycle.getExpressNum().equals("")) {
                        SendOrderInfoResp updateResp = FastJsonUtil.parseObject(expressService.updateOrder(orderInfo), SendOrderInfoResp.class);
                        if (updateResp.getResultCode() == null || !updateResp.getResultCode().equals("1000")) {
                            return AjaxResult.error("修改失败：" + updateResp.getReason());
                        }
                    }
                    break;
                default:
                    return AjaxResult.error("参数错误", orderStatus);
            }

            recycle.setUpdateTime(DateUtils.getNowDate());
            return toAjax(funRecycleService.updateFunRecycle(recycle));
        }
        return AjaxResult.error("订单不存在", orderID);
    }

    @GetMapping("/sendOrder")
    @ResponseBody
    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void sendRequest() throws AlipayApiException {

        new Thread(() -> {
            //延迟发送订单
            try {
                ordersService.queryOrderStatue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //每隔半小时定时查询订单状态
        if (new Date().getHours() < 9 || new Date().getHours() > 19)
            return;

        new Thread(() -> {
            //延迟发送订单
            try {
                funRecycleService.sendRequestRegular();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
