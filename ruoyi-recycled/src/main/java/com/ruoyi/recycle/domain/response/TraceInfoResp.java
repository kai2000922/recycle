/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.response;

/**
 * Auto-generated: 2021-11-03 1:10:8
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TraceInfoResp {

    private String result;
    private String reason;
    private String resultCode;
    private TraceQueryResponseParam responseParam;
    private String uniquerRequestNumber;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResponseParam(TraceQueryResponseParam responseParam) {
        this.responseParam = responseParam;
    }

    public TraceQueryResponseParam getResponseParam() {
        return responseParam;
    }

    public void setUniquerRequestNumber(String uniquerRequestNumber) {
        this.uniquerRequestNumber = uniquerRequestNumber;
    }

    public String getUniquerRequestNumber() {
        return uniquerRequestNumber;
    }

}