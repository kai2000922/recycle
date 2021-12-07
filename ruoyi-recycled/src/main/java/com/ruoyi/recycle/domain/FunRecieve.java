package com.ruoyi.recycle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收货地址信息对象 fun_recieve
 *
 * @author ruoyi
 * @date 2021-10-08
 */
public class FunRecieve extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long receiveID;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phone;

    /**
     * 省份
     */
    @Excel(name = "省份")
    private String prov;

    /**
     * 城市
     */
    @Excel(name = "城市")
    private String city;

    /**
     * 地区
     */
    @Excel(name = "地区")
    private String area;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String address;

    /**
     * 管辖地区
     */
    @Excel(name = "管辖地区")
    private String control;

    /**
     * 计数
     */
    @Excel(name = "计数")
    private Long counter;

    public void setReceiveID(Long receiveID) {
        this.receiveID = receiveID;
    }

    public Long getReceiveID() {
        return receiveID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
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

    public void setControl(String control) {
        this.control = control;
    }

    public String getControl() {
        return control;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("receiveID", getReceiveID())
                .append("name", getName())
                .append("phone", getPhone())
                .append("prov", getProv())
                .append("city", getCity())
                .append("area", getArea())
                .append("address", getAddress())
                .append("control", getControl())
                .append("counter", getCounter())
                .toString();
    }
}
