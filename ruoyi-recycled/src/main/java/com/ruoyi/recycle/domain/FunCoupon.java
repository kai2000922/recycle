package com.ruoyi.recycle.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠券配置对象 fun_coupon
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
public class FunCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long couponId;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String couponName;

    /** 使用门槛 */
    @Excel(name = "使用门槛")
    private BigDecimal maxPrice;

    /** 实际金额 */
    @Excel(name = "实际金额")
    private BigDecimal actPrice;

    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private Long numbers;

    /** 优惠券ID */
    private String templates;

    /** 有效日期 */
    @Excel(name = "有效日期")
    private String usedDay;

    /** 是否配置 */
    @Excel(name = "是否配置")
    private String isUsed;

    public void setCouponId(Long couponId) 
    {
        this.couponId = couponId;
    }

    public Long getCouponId() 
    {
        return couponId;
    }
    public void setCouponName(String couponName) 
    {
        this.couponName = couponName;
    }

    public String getCouponName() 
    {
        return couponName;
    }
    public void setMaxPrice(BigDecimal maxPrice) 
    {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMaxPrice() 
    {
        return maxPrice;
    }
    public void setActPrice(BigDecimal actPrice) 
    {
        this.actPrice = actPrice;
    }

    public BigDecimal getActPrice() 
    {
        return actPrice;
    }
    public void setNumbers(Long numbers) 
    {
        this.numbers = numbers;
    }

    public Long getNumbers() 
    {
        return numbers;
    }
    public void setTemplates(String templates) 
    {
        this.templates = templates;
    }

    public String getTemplates() 
    {
        return templates;
    }
    public void setUsedDay(String usedDay) 
    {
        this.usedDay = usedDay;
    }

    public String getUsedDay() 
    {
        return usedDay;
    }
    public void setIsUsed(String isUsed) 
    {
        this.isUsed = isUsed;
    }

    public String getIsUsed() 
    {
        return isUsed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("couponId", getCouponId())
            .append("couponName", getCouponName())
            .append("maxPrice", getMaxPrice())
            .append("actPrice", getActPrice())
            .append("numbers", getNumbers())
            .append("templates", getTemplates())
            .append("usedDay", getUsedDay())
            .append("isUsed", getIsUsed())
            .append("createTime", getCreateTime())
            .toString();
    }
}
