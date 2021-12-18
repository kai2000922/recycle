package com.ruoyi.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.recycle.config.AliPayApiConfig;
import com.ruoyi.recycle.domain.FunGoods;
import com.ruoyi.recycle.domain.request.TemplateMessageInfo;
import com.ruoyi.recycle.service.IAliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ali")
@Api("会员支付相关")
public class FunAliController {

    @Resource
    private IAliPayService aliPayService;

    private static final Logger log = LoggerFactory.getLogger(FunAliController.class);

    @PostMapping("/queryOrderNum")
    @ApiOperation(value = "获取支付订单单号", notes = "返回一笔交易订单号，根据该订单号发起支付")
    @ApiImplicitParams({@ApiImplicitParam(name = "funGoods", dataType = "FunGoods", dataTypeClass = FunGoods.class), @ApiImplicitParam(name = "userID", dataType = "String")})
    public AjaxResult pay(String title, double price, String userID) {
        if (StringUtils.isEmpty(userID)) {
            log.error("订单ID为空");
            return AjaxResult.success();
        }
        String order = aliPayService.getTradeNo(title, price, userID);
        if (order == null) {
            return AjaxResult.success("获取订单失败！");
        } else {
            return AjaxResult.success(order);
        }
    }

    @PostMapping("/auth")
    @ApiOperation(value = "获取用户支付宝ID", notes = "返回用户唯一ID")
    @ApiImplicitParam(name = "authCode", type = "String")
    public AjaxResult auth(String authCode) {
        String userID = aliPayService.getUserIDByAuthCode(authCode);
        if (userID == null)
            return AjaxResult.error("查询用户ID失败");
        else
            return AjaxResult.success(userID);
    }

    @PostMapping("/queryOrderStatus")
    @ApiOperation(value = "查询订单状态", notes = "根据状态码判断是否查询成功，查询成功后根据订单状态判断是否支付成功")
    @ApiImplicitParam(name = "orderNo", type = "String")
    public AjaxResult query(String orderNo) {
        AlipayTradeQueryResponse response = aliPayService.queryOrder(orderNo);
        if (response == null)
            return AjaxResult.error("查询订单状态失败");
        else
            return AjaxResult.success(response.getTradeStatus());
    }

    @GetMapping("/createCoupon")
    @ResponseBody
    public AjaxResult createCoupon() {
        return AjaxResult.success();
    }

    @GetMapping("/sendCoupon")
    public AjaxResult sendCoupon(String userID) {
        aliPayService.sendCoupon(userID, AliPayApiConfig.TEMPLATE_ID_30);
        return AjaxResult.success();
    }

    @GetMapping("/sendMessage")
    @ResponseBody
    public String sendMessage() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("value", "1200");
        jsonObject.put("keyword1", jsonObject1);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("value", "1200");
        jsonObject.put("keyword2", jsonObject3);

        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("value", "1200");
        jsonObject.put("keyword3", jsonObject5);
        System.out.println(FastJsonUtil.toJSONString(jsonObject));
        aliPayService.sendMessage("2088222014819702", "MjA4ODIyMjAxNDgxOTcwMl8xNjM3NzcyMjM2NzYzXzk2OA==", AliPayApiConfig.Order_Cancel,
                AliPayApiConfig.indexPage, jsonObject.toJSONString());
        return "";
    }

    @GetMapping("/sendOrderMessage")
    @ResponseBody
    public void sendOrderMessage() {
        TemplateMessageInfo templateMessageInfo = new TemplateMessageInfo();
        templateMessageInfo.setUserID("2088222014819702");
        templateMessageInfo.setMailNo("DPK202023493529");
        templateMessageInfo.setAuthCode("MjA4ODIyMjAxNDgxOTcwMl8xNjM3ODQxMzE5NjU1Xzk3OQ==");
        aliPayService.sendOrderMessage(templateMessageInfo);
    }

}
