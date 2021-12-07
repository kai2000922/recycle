/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.response;

import com.ruoyi.recycle.domain.request.SendOrderInfoReq;

/**
 * Auto-generated: 2021-11-01 23:9:1
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class QueryOrderInfoResp {

    private SendOrderInfoReq responseParam;
    private String reason;
    private String result;
    private String resultCode;

    public void setResponseParam(SendOrderInfoReq responseParam) {
        this.responseParam = responseParam;
    }

    public SendOrderInfoReq getResponseParam() {
        return responseParam;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }

}