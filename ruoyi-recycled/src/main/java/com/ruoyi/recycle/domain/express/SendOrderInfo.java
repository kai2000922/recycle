/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.express;

/**
 * Auto-generated: 2021-11-01 17:9:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SendOrderInfo {

    private String companyCode;
    private String custOrderNo;
    private String customerCode;
    private String logisticID;
    private int needTraceInfo;
    private String orderType;
    private PackageInfo packageInfo;
    private Receiver receiver;
    private Sender sender;
    private String transportType;
    private String gmtCommit;
    private String payType;
    private String isOut;
    private String remark;

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCustOrderNo(String custOrderNo) {
        this.custOrderNo = custOrderNo;
    }

    public String getCustOrderNo() {
        return custOrderNo;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setLogisticID(String logisticID) {
        this.logisticID = logisticID;
    }

    public String getLogisticID() {
        return logisticID;
    }

    public void setNeedTraceInfo(int needTraceInfo) {
        this.needTraceInfo = needTraceInfo;
    }

    public int getNeedTraceInfo() {
        return needTraceInfo;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Sender getSender() {
        return sender;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setGmtCommit(String gmtCommit) {
        this.gmtCommit = gmtCommit;
    }

    public String getGmtCommit() {
        return gmtCommit;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public String getIsOut() {
        return isOut;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}