package com.ruoyi.recycle.service.impl;

import com.alipay.api.AlipayApiException;
import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.deppon.dop.module.sdk.shared.util.SecurityUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.recycle.config.ExpressApiConfig;
import com.ruoyi.recycle.config.StatusConfig;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.express.CancelOrderInfo;
import com.ruoyi.recycle.domain.express.QueryOrderInfo;
import com.ruoyi.recycle.domain.express.SendOrderInfo;
import com.ruoyi.recycle.domain.express.TraceQueryInfo;
import com.ruoyi.recycle.domain.request.SendOrderInfoReq;
import com.ruoyi.recycle.domain.request.TemplateMessageInfo;
import com.ruoyi.recycle.service.IAliPayService;
import com.ruoyi.recycle.service.IExpressService;
import com.ruoyi.recycle.service.IFunUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class ExpressServiceImpl implements IExpressService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IFunUserService userService;
    @Autowired
    private IAliPayService aliPayService;

    @Override
    public String sendOrder(SendOrderInfo orderInfo) {
        return sendRequest(orderInfo, ExpressApiConfig.getCreateURL());
    }

    public String cancelOrder(FunRecycle recycle) {
        CancelOrderInfo cancelOrderInfo = new CancelOrderInfo();
        cancelOrderInfo.setLogisticID(recycle.getChannelNum());
        cancelOrderInfo.setLogisticCompanyID("DEPPON");
        cancelOrderInfo.setMailNo(recycle.getExpressNum());
        cancelOrderInfo.setRemark("");
        cancelOrderInfo.setCancelTime(DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS));

        return sendRequest(cancelOrderInfo, ExpressApiConfig.getCancelURL());
    }

    public String queryOrder(String logisticID) {
        QueryOrderInfo queryOrderInfo = new QueryOrderInfo(logisticID);

        return sendRequest(queryOrderInfo, ExpressApiConfig.getQueryURL());
    }

    @Override
    public String updateOrder(SendOrderInfo orderInfo) {
        return sendRequest(orderInfo, ExpressApiConfig.getUpdateURL());
    }

    public String sendRequest(Object obj, String url) {
        String params = null;
        if (obj != null) {
            params = FastJsonUtil.toJSONString(obj);
            logger.info("—————————————Request—————————————");
            logger.info(url);
            logger.info(params);
        }

        String companyCode = ExpressApiConfig.getCompanyCode();
        String appkey = ExpressApiConfig.getAppKey();
        String timestamp = String.valueOf(new Date().getTime());
        String digest = SecurityUtil.getStandardDigest(params + appkey + timestamp);

        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.set("params", params);
        request.set("companyCode", companyCode);
        request.set("timestamp", timestamp);
        request.set("digest", digest);

        String resp = restTemplate.postForObject(url, request, String.class);
        logger.info("—————————————Response—————————————");
        logger.info(url);
        logger.info(resp);
        return resp;
    }

    public String traceQuery(TraceQueryInfo info) {
        return sendRequest(info, ExpressApiConfig.getTraceURL());
    }

    //订单状态判断 http://dop.deppon.com/#/apiDocs/apiDetail/STANDARD_STATUS_PUSH
    public void judgeOrderStatus(SendOrderInfoReq sendOrderInfo, FunRecycle recycle) {
        //防止报空
        if (sendOrderInfo.getComments() == null)
            sendOrderInfo.setComments("");

        switch (sendOrderInfo.getStatusType()) {
            case "RECEIPTING":
                String comments = sendOrderInfo.getComments();
                if (StringUtils.isNotEmpty(comments))
                    recycle.setCourier(sendOrderInfo.getComments());
                if (comments.length() > 3) {
                    try {
                        String name = comments.substring(3, comments.length() - 11);
                        String phone = comments.substring(comments.length() - 11);
                        recycle.setCourier(name + " " + phone);
                    } catch (Exception e) {
                        logger.error("解析失败： " + comments);
                    }
                }
                recycle.setExpressStatus(StatusConfig.order_wait_get);
                recycle.setOrderStatus(StatusConfig.order_wait_get);
                break;
            case "GOT":
                recycle.setOrderStatus(StatusConfig.order_posting);
                recycle.setExpressStatus(StatusConfig.order_posting);
                if (sendOrderInfo.getTotalPrice() != 0) {
                    recycle.setExpressPrice((long) sendOrderInfo.getTotalPrice());
                }
                if (sendOrderInfo.getTotalVolume() != 0) {
                    recycle.setExpressVlo(String.valueOf(sendOrderInfo.getTotalVolume()));
                }
                if (recycle.getCourier().equals(""))
                    logger.error("接货中快递员为空");

                if (recycle.getBillingTime() == null) {
                    recycle.setBillingTime(new Date());
//                    aliPayService.sendCoupon(recycle.getUser());
                }

                recycle.setActualWeight((long) sendOrderInfo.getTotalWeight());

                TemplateMessageInfo templateMessageInfo = aliPayService.RecycleToTemplateInfo(recycle);
                aliPayService.sendOrderMessage(templateMessageInfo);

                try {
                    aliPayService.sendOrderReq(recycle,  "FINISHED");
                } catch (AlipayApiException e) {
                    e.printStackTrace();
                }

                //TODO
//                //判断重量是否达标
//                if (sendOrderInfo.getTotalWeight() >= 8) {
//                    recycle.setActualWeight((long) sendOrderInfo.getTotalWeight());
//                    FunUser user = null;
//                    try {
//                        user = userService.selectFunUserByUserNo(recycle.getUser());
//                        user.setRecycled(user.getRecycled()+1);
//                        userService.updateFunUser(user);
//                    }catch (Exception e) {
//                        logger.error("用户回收次数添加失败", user);
//                    }
//                }else {
//                    recycle.setActualWeight((long) sendOrderInfo.getTotalWeight());
//                    recycle.setOrderStatus(StatusConfig.order_be_cancel);
//                    recycle.setCacelReason("重量不足：" + sendOrderInfo.getTotalWeight());
//                }
                break;
            case "GOBACK":
            case "INVALID":
            case "REJECT":
                recycle.setOrderStatus(StatusConfig.order_be_cancel);
                recycle.setExpressStatus(StatusConfig.order_canceled);
                recycle.setCacelReason(sendOrderInfo.getComments());
                break;
            case "FAILGOT":
                recycle.setOrderStatus(StatusConfig.order_wait_get);
                recycle.setExpressStatus(StatusConfig.order_fail_got);
                recycle.setCacelReason(StatusConfig.order_fail_got + ":" + sendOrderInfo.getComments());
                break;
            case "CANCEL":
                recycle.setOrderStatus(StatusConfig.order_be_cancel);
                recycle.setExpressStatus(StatusConfig.order_be_cancel);
                recycle.setCacelReason("用户主动取消");
                break;
            case "ACCEPT":
                recycle.setOrderStatus(StatusConfig.order_wait_get);
                recycle.setExpressStatus(StatusConfig.order_wait_get);
                break;
            case "SIGNSUCCESS":
                recycle.setOrderStatus(StatusConfig.order_posting);
                recycle.setExpressStatus(StatusConfig.order_arrive);
                break;
            case "SIGNFAILED":
                recycle.setOrderStatus(StatusConfig.order_posting);
                recycle.setExpressStatus(StatusConfig.order_abnormal);
                break;
            default:
                logger.error("错误状态:" + sendOrderInfo);
        }
        recycle.setUpdateTime(new Date());
        recycle.setExpressUpdateTime(new Date());
    }

}
