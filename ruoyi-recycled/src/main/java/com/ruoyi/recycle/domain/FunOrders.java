package com.ruoyi.recycle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;

/**
 * 商城订单对象 fun_orders
 *
 * @author ruoyi
 * @date 2021-11-04
 */
public class FunOrders extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Excel(name = "订单ID")
    private Long ordersID;

    /**
     * 订单号码
     */
    @Excel(name = "订单号码")
    private Long ordersNum;

    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    @NotNull
    private Long goodsId;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 商品规格
     */
    @Excel(name = "商品规格")
    private String goodsType;

    /**
     * 支付价格
     */
    @Excel(name = "支付价格")
    @NotNull
    private double zfPrice;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    @NotNull
    private Long userId;

    /**
     * 用户名称
     */
    @Excel(name = "用户名称")
    @NotNull
    private String userName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    @NotNull
    private String userPhone;

    /**
     * 用户地址
     */
    @Excel(name = "用户地址")
    @NotNull
    private String userAddress;

    /**
     * 支付宝订单号码
     */
    @Excel(name = "支付宝订单号码")
    @NotNull
    private String tradeNo;

    /**
     * 运单号码
     */
    @Excel(name = "运单号码")
    private String expressNum;

    /**
     * 渠道来源
     */
    @Excel(name = "渠道来源")
    private String channel;

    /**
     * 订单状态
     */
    @Excel(name = "订单状态")
    @NotNull
    private String ordersStatus;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String mark;

    private String statu;

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public void setOrdersID(Long ordersID) {
        this.ordersID = ordersID;
    }

    public Long getOrdersID() {
        return ordersID;
    }

    public void setOrdersNum(Long ordersNum) {
        this.ordersNum = ordersNum;
    }

    public Long getOrdersNum() {
        return ordersNum;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setZfPrice(double zfPrice) {
        this.zfPrice = zfPrice;
    }

    public double getZfPrice() {
        return zfPrice;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }

    public String getOrdersStatus() {
        return ordersStatus;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public FunOrders() {
    }

    public FunOrders(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ordersID", getOrdersID())
                .append("ordersNum", getOrdersNum())
                .append("goodsId", getGoodsId())
                .append("goodsName", getGoodsName())
                .append("goodsType", getGoodsType())
                .append("zfPrice", getZfPrice())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("userPhone", getUserPhone())
                .append("userAddress", getUserAddress())
                .append("tradeNo", getTradeNo())
                .append("expressNum", getExpressNum())
                .append("channel", getChannel())
                .append("ordersStatus", getOrdersStatus())
                .append("mark", getMark())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
