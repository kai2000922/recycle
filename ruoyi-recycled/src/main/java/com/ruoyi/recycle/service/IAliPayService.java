package com.ruoyi.recycle.service;

import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayMerchantOrderSyncResponse;
import com.alipay.api.response.AlipayOpenAppMiniTemplatemessageSendResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.request.TemplateMessageInfo;


public interface IAliPayService {

    String getTradeNo(String title, double price, String userID);

    String getUserIDByAuthCode(String authCode);

    AlipayTradeQueryResponse getOrderStatus(String orderNo);

    AlipayTradeRefundResponse refundOrder(String tradeNo);

    void createCoupon();

    void sendCoupon(String userID);

    AlipayOpenAppMiniTemplatemessageSendResponse sendMessage(String userID, String formID, String templateID, String page, Object data);

    AlipayMerchantOrderSyncResponse sendOrderMessage(TemplateMessageInfo templateMessageInfo);

    TemplateMessageInfo RecycleToTemplateInfo(FunRecycle funRecycle);

    AlipayResponse sendRequest(AlipayRequest request, String action);
}
