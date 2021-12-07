package com.ruoyi.recycle.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.*;
import com.alipay.api.domain.AlipayMerchantOrderSyncModel;
import com.alipay.api.domain.ItemOrderInfo;
import com.alipay.api.domain.OrderExtInfo;
import com.alipay.api.domain.OrderLogisticsInformationRequest;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.recycle.config.AliPayApiConfig;
import com.ruoyi.recycle.config.StatusConfig;
import com.ruoyi.recycle.domain.FunOrders;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.request.TemplateMessageInfo;
import com.ruoyi.recycle.service.IAliPayService;
import com.ruoyi.recycle.service.IFunOrdersService;
import com.ruoyi.recycle.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AliPayServiceImpl implements IAliPayService {

    private static final Logger log = LoggerFactory.getLogger(AliPayServiceImpl.class);

    private static final AlipayClient alipayClient = new DefaultAlipayClient(AliPayApiConfig.URL, AliPayApiConfig.APPID, AliPayApiConfig.APP_PRIVATE_KEY, AliPayApiConfig.FORMAT, AliPayApiConfig.CHARSET, AliPayApiConfig.ALIPAY_PUBLIC_KEY, AliPayApiConfig.SIGN_TYPE);

    @Autowired
    private IFunOrdersService ordersService;

    @Override
    public String getTradeNo(String title, double price, String userID) {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", CommonUtil.getUniqueNum());
        bizContent.put("total_amount", price);
        bizContent.put("subject", title);
        bizContent.put("buyer_id", userID);
        bizContent.put("timeout_express", "10m");

        request.setBizContent(bizContent.toString());

        AlipayTradeCreateResponse response = (AlipayTradeCreateResponse) sendRequest(request, "获取订单号码");

        if (response.getTradeNo() != null)
            return response.getTradeNo();
        else {
            return null;
        }

    }

    @Override
    public String getUserIDByAuthCode(String authCode) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        AlipaySystemOauthTokenResponse response = (AlipaySystemOauthTokenResponse) sendRequest(request, "获取支付宝ID");
        if (response.getAlipayUserId() != null) {
            return response.getUserId();
        } else {
            log.error("认证失败：" + FastJsonUtil.toJSONString(response));
            return null;
        }
    }

    @Override
    public AlipayTradeQueryResponse getOrderStatus(String orderNo) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", orderNo);
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = (AlipayTradeQueryResponse) sendRequest(request, "查询订单状态");
        return response;
    }

    @Override
    public AlipayTradeRefundResponse refundOrder(String tradeNo) {
        FunOrders orders = null;
        try {
            orders = ordersService.selectFunOrdersList(new FunOrders(tradeNo)).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("未查询到订单信息 tradeNO:" + tradeNo);
            return null;
        }
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", orders.getTradeNo());
        bizContent.put("refund_amount", orders.getZfPrice());

        request.setBizContent(bizContent.toString());
        return (AlipayTradeRefundResponse) sendRequest(request, "退款");
    }

    public void createCoupon() {
        AlipayMarketingCashlessvoucherTemplateCreateRequest request = new AlipayMarketingCashlessvoucherTemplateCreateRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("voucher_type", "CASHLESS_FIX_VOUCHER");
        bizContent.put("brand_name", "回收小鸽");
        JSONObject period = new JSONObject();
        period.put("type", "RELATIVE");
        period.put("duration", "7");
        period.put("unit", "DAY");
        bizContent.put("voucher_valid_period", period.toJSONString());
        bizContent.put("publish_start_time", DateUtils.getTime());
        Date date = new Date();
        date.setMonth(date.getMonth() + 2);
        bizContent.put("publish_end_time", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, date));
        bizContent.put("out_biz_no", CommonUtil.getUniqueNum());
        bizContent.put("voucher_description", "[\"1、本券不可兑换现金，不可找零。\",\"2、每个用户最多可以领取1张。\",\"3、如果订单发生退款，优惠券无法退还。\"]");
        bizContent.put("voucher_quantity", "1000");
        bizContent.put("floor_amount", "39.9");
        bizContent.put("amount", "30");
        bizContent.put("voucher_available_time", "[]");
        bizContent.put("rule_conf", "{\"PID\": \"2088241774172948\"}");
        request.setBizContent(bizContent.toString());
        AlipayMarketingCashlessvoucherTemplateCreateResponse response = (AlipayMarketingCashlessvoucherTemplateCreateResponse) sendRequest(request, "创建优惠券");
    }

    public void sendCoupon(String userID) {
        AlipayMarketingVoucherSendRequest request = new AlipayMarketingVoucherSendRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("template_id", AliPayApiConfig.TEMPLATE_ID);
        jsonObject.put("user_id", userID);
        jsonObject.put("out_biz_no", CommonUtil.getUniqueNum());
        request.setBizContent(jsonObject.toString());

        AlipayMarketingVoucherSendResponse response = (AlipayMarketingVoucherSendResponse) sendRequest(request, "发送优惠券");
    }

    public AlipayResponse sendRequest(AlipayRequest request, String action) {
        log.info("——————————————" + action + "——————————————");
        log.info(String.valueOf(request.getTextParams()));

        try {
            AlipayResponse response = alipayClient.execute(request);
            log.info("—————————————response———————————————");
            log.info(FastJsonUtil.toJSONString(response));
            return response;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AlipayOpenAppMiniTemplatemessageSendResponse sendMessage(String userID, String formID, String templateID, String page, Object data) {
        AlipayOpenAppMiniTemplatemessageSendRequest request = new AlipayOpenAppMiniTemplatemessageSendRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("to_user_id", userID);
        jsonObject.put("form_id", formID);
        jsonObject.put("user_template_id", templateID);
        jsonObject.put("page", page);
        jsonObject.put("data", data);
        request.setBizContent(jsonObject.toString());
        return (AlipayOpenAppMiniTemplatemessageSendResponse) sendRequest(request, "发送模板消息");
    }

    public AlipayMerchantOrderSyncResponse sendOrderMessage(TemplateMessageInfo templateMessageInfo) {

        if (StringUtils.isEmpty(templateMessageInfo.getUserID()) || StringUtils.isEmpty(templateMessageInfo.getAuthCode()) || StringUtils.isEmpty(templateMessageInfo.getStatus())
                || StringUtils.isEmpty(templateMessageInfo.getBizType()) || StringUtils.isEmpty(templateMessageInfo.getPayAmount())
                || StringUtils.isEmpty(templateMessageInfo.getOutBizNum())) {
            log.error("参数错误，取消发送订单消息：" + JSON.toJSONString(templateMessageInfo));
            return null;
        }

        AlipayMerchantOrderSyncRequest request = new AlipayMerchantOrderSyncRequest();
        AlipayMerchantOrderSyncModel model = new AlipayMerchantOrderSyncModel();

        //base_info
        if (templateMessageInfo.getCreateTime() == null)
            model.setOrderCreateTime(DateUtils.getNowDate());
        else
            model.setOrderCreateTime(templateMessageInfo.getCreateTime());
        model.setOrderModifiedTime(DateUtils.getNowDate());

        model.setOutBizNo(templateMessageInfo.getOutBizNum());
        model.setBuyerId(templateMessageInfo.getUserID());
        model.setOrderType("SERVICE_ORDER");
        model.setOrderAuthCode(templateMessageInfo.getAuthCode());
        model.setPayAmount(templateMessageInfo.getPayAmount());
        model.setAmount("1");

        //mail_info
        if (StringUtils.isNotEmpty(templateMessageInfo.getMailNo())) {
            List<OrderLogisticsInformationRequest> logisticsInfoList = new ArrayList<OrderLogisticsInformationRequest>();
            OrderLogisticsInformationRequest TrackingNoList = new OrderLogisticsInformationRequest();
            TrackingNoList.setTrackingNo(templateMessageInfo.getMailNo());
            logisticsInfoList.add(TrackingNoList);
            model.setLogisticsInfoList(logisticsInfoList);
        }

        //ext_info
        List<OrderExtInfo> orderExtInfos = new ArrayList<>();

        OrderExtInfo merchant_order_status = new OrderExtInfo();
        merchant_order_status.setExtKey("merchant_order_status");
        merchant_order_status.setExtValue(templateMessageInfo.getStatus());
        orderExtInfos.add(merchant_order_status);

        OrderExtInfo tiny_app_id = new OrderExtInfo();
        tiny_app_id.setExtKey("tiny_app_id");
        tiny_app_id.setExtValue(AliPayApiConfig.APPID);
        orderExtInfos.add(tiny_app_id);

        if (StringUtils.isNotEmpty(templateMessageInfo.getPages())) {
            OrderExtInfo merchant_order_link_page = new OrderExtInfo();
            merchant_order_link_page.setExtKey("merchant_order_link_page");
            merchant_order_link_page.setExtValue(templateMessageInfo.getPages());
            orderExtInfos.add(merchant_order_link_page);
        }

        OrderExtInfo merchant_biz_type = new OrderExtInfo();
        merchant_biz_type.setExtKey("merchant_biz_type");
        merchant_biz_type.setExtValue(templateMessageInfo.getBizType());
        orderExtInfos.add(merchant_biz_type);

        OrderExtInfo business_info = new OrderExtInfo();
        business_info.setExtKey("business_info");
        business_info.setExtValue("{\"platform_phone\":\"400xxxxxxx\",\"platform_name\":\"回收小鸽\",\"door_start_time\":" + "\"" + templateMessageInfo.getExpectTime() + "\"" + "}");
        orderExtInfos.add(business_info);

        //item_info
        List<ItemOrderInfo> itemOrderList = new ArrayList<>();
        ItemOrderInfo itemOrderInfo = new ItemOrderInfo();
        itemOrderInfo.setItemName("衣服");
        itemOrderInfo.setQuantity(templateMessageInfo.getQuantity());
        itemOrderInfo.setUnitPrice("1");

        //item_extra_info
        List<OrderExtInfo> item_extra_info = new ArrayList<>();
        OrderExtInfo goodsDesc = new OrderExtInfo();
        goodsDesc.setExtKey("goodsDesc");
        goodsDesc.setExtValue("回收衣物");
        item_extra_info.add(goodsDesc);
        OrderExtInfo image_material_id = new OrderExtInfo();
        image_material_id.setExtKey("image_material_id");
        image_material_id.setExtValue(AliPayApiConfig.ImageID);
        item_extra_info.add(goodsDesc);
        item_extra_info.add(image_material_id);

        itemOrderInfo.setExtInfo(item_extra_info);
        itemOrderList.add(itemOrderInfo);

        model.setExtInfo(orderExtInfos);
        model.setItemOrderList(itemOrderList);

        request.setBizModel(model);
        return (AlipayMerchantOrderSyncResponse) sendRequest(request, "发送订单消息");
    }

    public TemplateMessageInfo RecycleToTemplateInfo(FunRecycle funRecycle) {
        TemplateMessageInfo templateMessageInfo = new TemplateMessageInfo();
        templateMessageInfo.setUserID(funRecycle.getUser());
        if (StringUtils.isNotEmpty(funRecycle.getAuthCode()))
            templateMessageInfo.setAuthCode(funRecycle.getAuthCode());
        templateMessageInfo.setPages(AliPayApiConfig.OrderPage);
        templateMessageInfo.setBizType(AliPayApiConfig.DoorToDoor_BizType);
        templateMessageInfo.setPayAmount("0");
        templateMessageInfo.setQuantity(funRecycle.getExpectWeight());
        templateMessageInfo.setOutBizNum(funRecycle.getChannelNum());
        templateMessageInfo.setCreateTime(funRecycle.getCreateTime());
        if (StringUtils.isNotEmpty(funRecycle.getAuthCode()))
            templateMessageInfo.setAuthCode(funRecycle.getAuthCode());
        templateMessageInfo.setExpectTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, funRecycle.getExpectTime()));
        switch (funRecycle.getOrderStatus()) {
            case StatusConfig.order_wait_get:
                templateMessageInfo.setStatus("CREATE");
                break;
            case StatusConfig.order_be_cancel:
                templateMessageInfo.setStatus("CANCELED");
                break;
            case StatusConfig.order_posting:
                templateMessageInfo.setStatus("FINISHED");
                break;
            default:
                return null;
        }
        return templateMessageInfo;
    }

}
