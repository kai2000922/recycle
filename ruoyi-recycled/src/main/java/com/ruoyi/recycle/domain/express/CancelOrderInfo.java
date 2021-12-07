/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.express;

/**
 * Auto-generated: 2021-11-02 0:10:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CancelOrderInfo {

    private String cancelTime;
    private String logisticCompanyID;
    private String logisticID;
    private String mailNo;
    private String remark;

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setLogisticCompanyID(String logisticCompanyID) {
        this.logisticCompanyID = logisticCompanyID;
    }

    public String getLogisticCompanyID() {
        return logisticCompanyID;
    }

    public void setLogisticID(String logisticID) {
        this.logisticID = logisticID;
    }

    public String getLogisticID() {
        return logisticID;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}