package com.ruoyi.recycle.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.*;
import com.ruoyi.recycle.domain.FunCoupon;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.request.TemplateMessageInfo;


public interface IAliPayService {

    String getTradeNo(String title, double price, String userID);

    String getUserInfoByAuthCode(String authCode, int type);

    AlipayTradeQueryResponse queryOrder(String orderNo);

    AlipayTradeRefundResponse refundOrder(String tradeNo);

    AlipayMarketingCashlessvoucherTemplateCreateResponse createCoupon(FunCoupon coupon);

    void sendCoupon(String userID, String templateID);

    AlipayOpenAppMiniTemplatemessageSendResponse sendMessage(String userID, String formID, String templateID, String page, Object data);

    AlipayMerchantOrderSyncResponse sendOrderMessage(TemplateMessageInfo templateMessageInfo);

    TemplateMessageInfo RecycleToTemplateInfo(FunRecycle funRecycle);

    AlipayResponse sendRequest(AlipayRequest request, String action);

    public void createService() throws AlipayApiException;

    public void sendOrderReq(FunRecycle funRecycle, String status) throws AlipayApiException;

}
