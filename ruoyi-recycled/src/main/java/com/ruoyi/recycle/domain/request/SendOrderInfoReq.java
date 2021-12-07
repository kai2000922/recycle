/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.request;

import com.ruoyi.recycle.domain.express.Receiver;
import com.ruoyi.recycle.domain.express.Sender;

import java.util.List;

/**
 * Auto-generated: 2021-11-02 0:58:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SendOrderInfoReq {

    private String backSignBill;
    private String businessNetworkNo;
    private String cargoName;
    private String codPrice;
    private String codValue;
    private String comments;
    private String deliveryPrice;
    private String gmtUpdated;
    private List<String> goodsList;
    private String insurancePrice;
    private String insuranceValue;
    private String logisticCompanyID;
    private String logisticID;
    private String mailNo;
    private String otherPrice;
    private String packageService;
    private String packageServicePrice;
    private String payType;
    private Receiver receiver;
    private Sender sender;
    private String smsNotify;
    private String smsNotifyPrice;
    private String statusType;
    private String toNetworkNo;
    private String totalNumber;
    private double totalPrice;
    private double totalVolume;
    private double totalWeight;
    private String transportPrice;
    private String transportType;
    private String vistReceive;
    private String vistReceivePrice;
    private String waitNotifySend;

    public void setBackSignBill(String backSignBill) {
        this.backSignBill = backSignBill;
    }

    public String getBackSignBill() {
        return backSignBill;
    }

    public void setBusinessNetworkNo(String businessNetworkNo) {
        this.businessNetworkNo = businessNetworkNo;
    }

    public String getBusinessNetworkNo() {
        return businessNetworkNo;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCodPrice(String codPrice) {
        this.codPrice = codPrice;
    }

    public String getCodPrice() {
        return codPrice;
    }

    public void setCodValue(String codValue) {
        this.codValue = codValue;
    }

    public String getCodValue() {
        return codValue;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setGmtUpdated(String gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }

    public String getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGoodsList(List<String> goodsList) {
        this.goodsList = goodsList;
    }

    public List<String> getGoodsList() {
        return goodsList;
    }

    public void setInsurancePrice(String insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public String getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsuranceValue(String insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getInsuranceValue() {
        return insuranceValue;
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

    public void setOtherPrice(String otherPrice) {
        this.otherPrice = otherPrice;
    }

    public String getOtherPrice() {
        return otherPrice;
    }

    public void setPackageService(String packageService) {
        this.packageService = packageService;
    }

    public String getPackageService() {
        return packageService;
    }

    public void setPackageServicePrice(String packageServicePrice) {
        this.packageServicePrice = packageServicePrice;
    }

    public String getPackageServicePrice() {
        return packageServicePrice;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
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

    public void setSmsNotify(String smsNotify) {
        this.smsNotify = smsNotify;
    }

    public String getSmsNotify() {
        return smsNotify;
    }

    public void setSmsNotifyPrice(String smsNotifyPrice) {
        this.smsNotifyPrice = smsNotifyPrice;
    }

    public String getSmsNotifyPrice() {
        return smsNotifyPrice;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setToNetworkNo(String toNetworkNo) {
        this.toNetworkNo = toNetworkNo;
    }

    public String getToNetworkNo() {
        return toNetworkNo;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTransportPrice(String transportPrice) {
        this.transportPrice = transportPrice;
    }

    public String getTransportPrice() {
        return transportPrice;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setVistReceive(String vistReceive) {
        this.vistReceive = vistReceive;
    }

    public String getVistReceive() {
        return vistReceive;
    }

    public void setVistReceivePrice(String vistReceivePrice) {
        this.vistReceivePrice = vistReceivePrice;
    }

    public String getVistReceivePrice() {
        return vistReceivePrice;
    }

    public void setWaitNotifySend(String waitNotifySend) {
        this.waitNotifySend = waitNotifySend;
    }

    public String getWaitNotifySend() {
        return waitNotifySend;
    }

}