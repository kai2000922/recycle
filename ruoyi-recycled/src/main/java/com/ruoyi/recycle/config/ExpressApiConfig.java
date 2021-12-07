package com.ruoyi.recycle.config;

public class ExpressApiConfig {

    private static final String createURL = "http://gwapi.deppon.com/dop-interface-async/standard-order/createOrderNotify.action";
    private static final String updateURL = "http://gwapi.deppon.com/dop-interface-async/standard-order/updateOrder.action";
    private static final String cancelURL = "http://gwapi.deppon.com/dop-interface-async/standard-order/cancelOrder.action";
    private static final String queryURL = "http://dpapi.deppon.com/dop-interface-sync/standard-query/queryOrder.action";
    private static final String traceURL = "http://dpapi.deppon.com/dop-interface-sync/standard-query/newTraceQuery.action";

//    private static final String createURL = "http://dpsanbox.deppon.com/sandbox-web/dop-standard-ewborder/createOrderNotify.action";
//    private static final String updateURL = "http://dpsanbox.deppon.com/sandbox-web/standard-order/updateOrder.action";
//    private static final String cancelURL = "http://dpsanbox.deppon.com/sandbox-web/standard-order/cancelOrder.action";
//    private static final String queryURL = "http://dpsanbox.deppon.com/sandbox-web/standard-order/queryOrder.action";
//    private static final String traceURL = "http://dpsanbox.deppon.com/sandbox-web/standard-order/newTraceQuery.action";


    private static final String Count = "892960723";
    private static final String sign = "FYSD";
    private static final String companyCode = "EWBHSXG";
    private static final String appKey = "fc1300d60851e20436e566b5894c24ea";

    public static String getCompanyCode() {
        return companyCode;
    }

    public static String getAppKey() {
        return appKey;
    }

    public static String getSign() {
        return sign;
    }

    public static String getCreateURL() {
        return createURL;
    }

    public static String getUpdateURL() {
        return updateURL;
    }

    public static String getCancelURL() {
        return cancelURL;
    }

    public static String getQueryURL() {
        return queryURL;
    }

    public static String getCount() {
        return Count;
    }

    public static String getTraceURL() {
        return traceURL;
    }
}
