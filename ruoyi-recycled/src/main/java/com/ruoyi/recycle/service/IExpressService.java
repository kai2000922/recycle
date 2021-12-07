package com.ruoyi.recycle.service;

import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.express.SendOrderInfo;
import com.ruoyi.recycle.domain.express.TraceQueryInfo;
import com.ruoyi.recycle.domain.request.SendOrderInfoReq;

public interface IExpressService {

    String sendOrder(SendOrderInfo orderInfo);

    String cancelOrder(FunRecycle recycle);

    String updateOrder(SendOrderInfo orderInfo);

    String queryOrder(String logisticID);

    String traceQuery(TraceQueryInfo info);

    void judgeOrderStatus(SendOrderInfoReq sendOrderInfo, FunRecycle recycle);

}
