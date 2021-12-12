package com.ruoyi.recycle.controller;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.config.AliPayApiConfig;
import com.ruoyi.recycle.config.StatusConfig;
import com.ruoyi.recycle.domain.FunOrders;
import com.ruoyi.recycle.service.IAliPayService;
import com.ruoyi.recycle.service.IFunOrdersService;
import com.ruoyi.recycle.service.IFunUserService;
import com.ruoyi.recycle.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.CODE_TAG;
import static com.ruoyi.common.core.domain.AjaxResult.MSG_TAG;

/**
 * 商城订单Controller
 *
 * @author ruoyi
 * @date 2021-11-03
 */
@Controller
@RequestMapping("/recycle/orders")
@Api("商品信息")
public class FunOrdersController extends BaseController {
    private final String prefix = "recycle/orders";

    @Autowired
    private IFunOrdersService funOrdersService;
    @Autowired
    private IAliPayService aliPayService;
    @Autowired
    private IFunUserService userService;

    @RequiresPermissions("recycle:orders:view")
    @GetMapping()
    public String orders() {
        return prefix + "/orders";
    }

    /**
     * 查询商城订单列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunOrders funOrders) {
        //用户查询列表
        startPage();
        funOrders.setStatu("0");
        List<FunOrders> list = funOrdersService.selectFunOrdersList(funOrders);
        return getDataTable(list);
    }

    @RequiresPermissions("recycle:orders:list")
    @PostMapping("/listByAdmin")
    @ResponseBody
    public TableDataInfo listByAdmin(FunOrders funOrders) {
        //管理员查询列表
        startPage();
        List<FunOrders> list = funOrdersService.selectFunOrdersList(funOrders);
        return getDataTable(list);
    }

    /**
     * 导出商城订单列表
     */
    @RequiresPermissions("recycle:orders:export")
    @Log(title = "商城订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunOrders funOrders) {
        List<FunOrders> list = funOrdersService.selectFunOrdersList(funOrders);
        ExcelUtil<FunOrders> util = new ExcelUtil<FunOrders>(FunOrders.class);
        return util.exportExcel(list, "商城订单数据");
    }

    @RequiresPermissions("recycle:orders:export")
    @Log(title = "商城订单", businessType = BusinessType.EXPORT)
    @PostMapping("/exportUnExpress")
    @ResponseBody
    public AjaxResult exportUnExpress() {
        FunOrders funOrders = new FunOrders();
        funOrders.setOrdersStatus("未发货");
        //导出所有未发货数据
        List<FunOrders> list = funOrdersService.selectFunOrdersList(funOrders);
        ExcelUtil<FunOrders> util = new ExcelUtil<FunOrders>(FunOrders.class);
        AjaxResult result = util.exportExcel(list, "商城未发货订单数据");
        if ((Integer) result.get(CODE_TAG) != 0) {
            return AjaxResult.error((String) result.get(MSG_TAG));
        }

        //导出待发货订单后将状态修改为揽件中
        for (FunOrders orders : list) {
            orders.setOrdersStatus("揽件中");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    funOrdersService.updateFunOrders(orders);
                }
            }).start();
        }
        return result;
    }

    /**
     * 新增商城订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商城订单
     */
//    @RequiresPermissions("recycle:orders:add")
    @Log(title = "商城订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("创建商城订单")
    @ApiImplicitParam(name = "funOrders", dataType = "FunOrders", dataTypeClass = FunOrders.class)
    public AjaxResult addSave(@Validated FunOrders funOrders, BindingResult result) {
        String orderStatus = funOrders.getOrdersStatus();

        if (!orderStatus.equals("未发货") && !orderStatus.equals("待支付")) {
            return AjaxResult.error("参数错误", orderStatus);
        }

        funOrders.setStatu("0");
        funOrders.setOrdersNum(Long.valueOf(CommonUtil.getUniqueNumer()));

        try {
            funOrdersService.insertFunOrders(funOrders);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("创建商城订单失败: " + e.getMessage());
            return AjaxResult.error("创建订单失败，请重试或联系在线客服！");
        }
        return AjaxResult.success(funOrders.getOrdersID());
    }

    /**
     * 修改商城订单
     */
    @GetMapping("/edit/{ordersID}")
    public String edit(@PathVariable("ordersID") Long ordersID, ModelMap mmap) {
        FunOrders funOrders = funOrdersService.selectFunOrdersByOrdersID(ordersID);
        mmap.put("funOrders", funOrders);
        return prefix + "/edit";
    }

    /**
     * 修改保存商城订单
     */
    @Log(title = "商城订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunOrders funOrders) {
        FunOrders oldOrders = funOrdersService.selectFunOrdersByOrdersID(funOrders.getOrdersID());
        if (funOrdersService.updateFunOrders(funOrders) <= 0) {
            return AjaxResult.error("更新失败");
        }
        if (funOrders.getOrdersStatus().equals(StatusConfig.goods_refund)) {
            AlipayTradeRefundResponse response = aliPayService.refundOrder(funOrders.getTradeNo());
            if (response.getCode() == null || !response.getCode().equals("10000")) {
                oldOrders.setStatu(StatusConfig.goods_refund_succeed);
                funOrdersService.updateFunOrders(oldOrders);
                return AjaxResult.error("退款失败，更新状态已回滚：" + response.getSubMsg());
            }
        }
        return AjaxResult.success();
    }

    /**
     * 删除商城订单
     */
    @RequiresPermissions("recycle:orders:remove")
    @Log(title = "商城订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funOrdersService.deleteFunOrdersByOrdersIDs(ids));
    }

    @PostMapping("/refund")
    @ResponseBody
    @ApiOperation(value = "商品退款", notes = "仅支持修改订单状态为退款，返回成功后代表退款请求已受理，并不代表已经退款")
    @ApiImplicitParam(name = "funOrders", dataType = "FunOrders", dataTypeClass = FunOrders.class)
    public AjaxResult refund(FunOrders funOrders) {
        if (funOrders.getOrdersStatus().equals("退款中")) {
            FunOrders orders = funOrdersService.selectFunOrdersByOrdersID(funOrders.getOrdersID());
            if (orders == null) {
                return AjaxResult.error("订单不存在！", funOrders);
            }
            //只限未发货订单退款
            if (!orders.getOrdersStatus().equals("未发货"))
                return AjaxResult.error("暂不能退款，请确认订单状态！");
            AlipayTradeRefundResponse response = aliPayService.refundOrder(funOrders.getTradeNo());
            if (response == null || !response.getCode().equals("10000"))
                return AjaxResult.error("退款失败！");
            orders.setStatu(StatusConfig.goods_refund_succeed);
            return toAjax(funOrdersService.updateFunOrders(funOrders));
        }
        return AjaxResult.error("无效请求");
    }

    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<FunOrders> util = new ExcelUtil<>(FunOrders.class);
        List<FunOrders> ordersList = util.importExcel(file.getInputStream());
        String message = funOrdersService.importOrders(ordersList, updateSupport);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<FunOrders> util = new ExcelUtil<>(FunOrders.class);
        return util.importTemplateExcel("商城订单数据");
    }

    @PostMapping("/updateOrderStatus")
    @ResponseBody
    public AjaxResult updateOrderStatus(Long orderID, String tradeNo){
        FunOrders funOrders = funOrdersService.selectFunOrdersByOrdersID(orderID);
        if (funOrders == null)
            return AjaxResult.error("订单不存在");
        funOrders.setTradeNo(tradeNo);
        //查询订单状态
        AlipayTradeQueryResponse response = aliPayService.getOrderStatus(funOrders.getTradeNo());
        if (!response.getCode().equals("10000")) {
            return AjaxResult.error("查询订单失败！：" + response.getMsg());
        }

        if (response.getTradeStatus().equals(AliPayApiConfig.TRADE_SUCCESS)) {
            funOrders.setZfPrice(Double.parseDouble(response.getTotalAmount()));
            funOrders.setOrdersStatus("未发货");
        } else if (response.getTradeStatus().equals(AliPayApiConfig.WAIT_BUYER_PAY)) {
            funOrders.setOrdersStatus("待支付");
        }else {
            return AjaxResult.error("订单失效", response.getTradeStatus());
        }

        try {
            funOrdersService.updateFunOrders(funOrders);
        } catch (Exception e) {
            return AjaxResult.error("更新订单状态失败，请联系客服！", orderID);
        }

        return AjaxResult.success();
    }
}
