package com.ruoyi.recycle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物流价格对象 fun_express_price
 *
 * @author ruoyi
 * @date 2021-11-04
 */
public class FunExpressPrice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 出发事业部
     */
    @Excel(name = "出发事业部")
    private String outsetPlace;

    /**
     * 出发省份
     */
    @Excel(name = "出发省份")
    private String outsetProv;

    /**
     * 出发城市
     */
    @Excel(name = "出发城市")
    private String outsetCity;

    /**
     * 出发简称
     */
    @Excel(name = "出发简称")
    private String outsetName;

    /**
     * 到达省份
     */
    @Excel(name = "到达省份")
    private String arriveProv;

    /**
     * 到达城市
     */
    @Excel(name = "到达城市")
    private String arriveCity;

    /**
     * 到达简称
     */
    @Excel(name = "到达简称")
    private String arriveName;

    /**
     * 城市城市
     */
    @Excel(name = "城市城市")
    private String city2city;

    /**
     * 标快首重
     */
    @Excel(name = "标快首重")
    private String standardFirst;

    /**
     * 标快续重
     */
    @Excel(name = "标快续重")
    private String standardContinue;

    /**
     * 大件快递3.60首重
     */
    @Excel(name = "大件快递3.60首重")
    private String bigFirst;

    /**
     * 大件快递3.60续重
     */
    @Excel(name = "大件快递3.60续重")
    private String bigContinue;

    public FunExpressPrice() {
    }

    public FunExpressPrice(String city2city) {
        this.city2city = city2city;
    }

    public void setOutsetPlace(String outsetPlace) {
        this.outsetPlace = outsetPlace;
    }

    public String getOutsetPlace() {
        return outsetPlace;
    }

    public void setOutsetProv(String outsetProv) {
        this.outsetProv = outsetProv;
    }

    public String getOutsetProv() {
        return outsetProv;
    }

    public void setOutsetCity(String outsetCity) {
        this.outsetCity = outsetCity;
    }

    public String getOutsetCity() {
        return outsetCity;
    }

    public void setOutsetName(String outsetName) {
        this.outsetName = outsetName;
    }

    public String getOutsetName() {
        return outsetName;
    }

    public void setArriveProv(String arriveProv) {
        this.arriveProv = arriveProv;
    }

    public String getArriveProv() {
        return arriveProv;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
    }

    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveName(String arriveName) {
        this.arriveName = arriveName;
    }

    public String getArriveName() {
        return arriveName;
    }

    public void setCity2city(String city2city) {
        this.city2city = city2city;
    }

    public String getCity2city() {
        return city2city;
    }

    public void setStandardFirst(String standardFirst) {
        this.standardFirst = standardFirst;
    }

    public String getStandardFirst() {
        return standardFirst;
    }

    public void setStandardContinue(String standardContinue) {
        this.standardContinue = standardContinue;
    }

    public String getStandardContinue() {
        return standardContinue;
    }

    public void setBigFirst(String bigFirst) {
        this.bigFirst = bigFirst;
    }

    public String getBigFirst() {
        return bigFirst;
    }

    public void setBigContinue(String bigContinue) {
        this.bigContinue = bigContinue;
    }

    public String getBigContinue() {
        return bigContinue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("outsetPlace", getOutsetPlace())
                .append("outsetProv", getOutsetProv())
                .append("outsetCity", getOutsetCity())
                .append("outsetName", getOutsetName())
                .append("arriveProv", getArriveProv())
                .append("arriveCity", getArriveCity())
                .append("arriveName", getArriveName())
                .append("city2city", getCity2city())
                .append("standardFirst", getStandardFirst())
                .append("standardContinue", getStandardContinue())
                .append("bigFirst", getBigFirst())
                .append("bigContinue", getBigContinue())
                .toString();
    }
}
