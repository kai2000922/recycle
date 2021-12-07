package com.ruoyi.recycle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户信息对象 fun_user
 *
 * @author ruoyi
 * @date 2021-11-05
 */
public class FunUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userID;

    /**
     * 支付宝ID
     */
    @Excel(name = "支付宝ID")
    private String userNo;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String userName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String userPhone;

    /**
     * 最近使用地址
     */
    @Excel(name = "最近使用地址")
    private String recentAdd;

    /**
     * 优惠券
     */
    @Excel(name = "优惠券")
    private String coupon;

    /**
     * 已回收订单
     */
    @Excel(name = "已回收订单")
    private Long recycled;

    /**
     * 渠道来源
     */
    @Excel(name = "渠道来源")
    private String channel;

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserNo() {
        return userNo;
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

    public void setRecentAdd(String recentAdd) {
        this.recentAdd = recentAdd;
    }

    public String getRecentAdd() {
        return recentAdd;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setRecycled(Long recycled) {
        this.recycled = recycled;
    }

    public Long getRecycled() {
        return recycled;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userID", getUserID())
                .append("userNo", getUserNo())
                .append("userName", getUserName())
                .append("userPhone", getUserPhone())
                .append("recentAdd", getRecentAdd())
                .append("coupon", getCoupon())
                .append("recycled", getRecycled())
                .append("channel", getChannel())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
