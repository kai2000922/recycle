package com.ruoyi.recycle.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 回收订单对象 fun_recycle
 *
 * @author ruoyi
 * @date 2021-10-07
 */
public class FunRecycle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long recycleID;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    @NotNull
    private String user;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @NotNull
    private String name;

    /**
     * 省份
     */
    @NotNull
    private String prov;

    /**
     * 城市
     */
    @NotNull
    private String city;

    /**
     * 地区
     */
    @NotNull
    private String area;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    @NotNull
    private String address;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    @NotNull
    private String phone;

    /**
     * 提交重量
     */
    @Excel(name = "提交重量")
    @NotNull
    private Long expectWeight;

    /**
     * 物流实际重量
     */
    @Excel(name = "物流实际重量")
    private Long actualWeight;

    /**
     * 是否预约现在
     */
    @NotNull
    private String isNow;

    /**
     * 期望上门时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "期望上门时间", width = 30, dateFormat = "yyyy-MM-dd")
    @NotNull
    private Date expectTime;

    /**
     * 收货厂商ID
     */
    @Excel(name = "收货厂商ID")
    private String receiveAddId;

    /**
     * 运单号码
     */
    @Excel(name = "运单号码")
    private String expressNum;

    /**
     * 物流价格
     */
    @Excel(name = "物流价格")
    private Long expressPrice;

    /**
     * 物流体积重量
     */
    @Excel(name = "物流体积重量")
    private String expressVlo;

    /**
     * 物流状态
     */
    @Excel(name = "物流状态")
    private String expressStatus;

    /**
     * 物流最后更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "物流最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expressUpdateTime;

    /**
     * 快递员信息
     */
    @Excel(name = "快递员信息")
    private String courier;

    /**
     * 渠道单号
     */
    @Excel(name = "渠道单号")
    private String channelNum;

    /**
     * 渠道来源
     */
    @Excel(name = "渠道来源")
    private String channelSource;

    /**
     * 订单状态
     */
    @Excel(name = "订单状态")
    @NotNull
    private String orderStatus;

    /**
     * 取消原因
     */
    @Excel(name = "取消原因")
    private String cacelReason;

    /**
     * 开单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date billingTime;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String orderNum;

    private String statu;

    private String authCode;

    public FunRecycle() {
    }

    public FunRecycle(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public void setRecycleID(Long recycleID) {
        this.recycleID = recycleID;
    }

    public Long getRecycleID() {
        return recycleID;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getProv() {
        return prov;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setExpectWeight(Long expectWeight) {
        this.expectWeight = expectWeight;
    }

    public Long getExpectWeight() {
        return expectWeight;
    }

    public void setActualWeight(Long actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Long getActualWeight() {
        return actualWeight;
    }

    public void setIsNow(String isNow) {
        this.isNow = isNow;
    }

    public String getIsNow() {
        return isNow;
    }

    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    public Date getExpectTime() {
        return expectTime;
    }

    public void setReceiveAddId(String receiveAddId) {
        this.receiveAddId = receiveAddId;
    }

    public String getReceiveAddId() {
        return receiveAddId;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressPrice(Long expressPrice) {
        this.expressPrice = expressPrice;
    }

    public Long getExpressPrice() {
        return expressPrice;
    }

    public void setExpressVlo(String expressVlo) {
        this.expressVlo = expressVlo;
    }

    public String getExpressVlo() {
        return expressVlo;
    }

    public void setExpressStatus(String expressStatus) {
        this.expressStatus = expressStatus;
    }

    public String getExpressStatus() {
        return expressStatus;
    }

    public void setExpressUpdateTime(Date expressUpdateTime) {
        this.expressUpdateTime = expressUpdateTime;
    }

    public Date getExpressUpdateTime() {
        return expressUpdateTime;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCourier() {
        return courier;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelSource(String channelSource) {
        this.channelSource = channelSource;
    }

    public String getChannelSource() {
        return channelSource;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setCacelReason(String cacelReason) {
        this.cacelReason = cacelReason;
    }

    public String getCacelReason() {
        return cacelReason;
    }

    public void setBillingTime(Date billingTime) {
        this.billingTime = billingTime;
    }

    public Date getBillingTime() {
        return billingTime;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("recycleID", getRecycleID())
                .append("user", getUser())
                .append("name", getName())
                .append("prov", getProv())
                .append("city", getCity())
                .append("area", getArea())
                .append("address", getAddress())
                .append("phone", getPhone())
                .append("expectWeight", getExpectWeight())
                .append("actualWeight", getActualWeight())
                .append("isNow", getIsNow())
                .append("expectTime", getExpectTime())
                .append("receiveAddId", getReceiveAddId())
                .append("expressNum", getExpressNum())
                .append("expressPrice", getExpressPrice())
                .append("expressVlo", getExpressVlo())
                .append("expressStatus", getExpressStatus())
                .append("expressUpdateTime", getExpressUpdateTime())
                .append("courier", getCourier())
                .append("channelNum", getChannelNum())
                .append("channelSource", getChannelSource())
                .append("orderStatus", getOrderStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("cacelReason", getCacelReason())
                .append("billingTime", getBillingTime())
                .append("orderNum", getOrderNum())
                .toString();
    }
}
