package com.ruoyi.recycle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动页配置对象 fun_activity_config
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
public class FunActivityConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    @Excel(name = "配置ID")
    private Long configId;

    /** 配置序号 */
    @Excel(name = "配置序号")
    private Long configNum;

    /** 配置名称 */
    @Excel(name = "配置名称")
    private String configName;

    /** Banner */
    @Excel(name = "Banner")
    private String banners;

    /** 跳转界面 */
    @Excel(name = "跳转界面")
    private String toPages;

    /** 配置参数 */
    @Excel(name = "配置参数")
    private String param;

    /** 流程图 */
    @Excel(name = "流程图")
    private String process;

    /** 优惠券 */
    @Excel(name = "优惠券")
    private String coupon;

    /** 生成链接 */
    @Excel(name = "生成链接")
    private String links;

    /** 备注 */
    @Excel(name = "备注")
    private String mark;

    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }
    public void setConfigNum(Long configNum) 
    {
        this.configNum = configNum;
    }

    public Long getConfigNum() 
    {
        return configNum;
    }
    public void setConfigName(String configName) 
    {
        this.configName = configName;
    }

    public String getConfigName() 
    {
        return configName;
    }
    public void setBanners(String banners) 
    {
        this.banners = banners;
    }

    public String getBanners() 
    {
        return banners;
    }
    public void setToPages(String toPages) 
    {
        this.toPages = toPages;
    }

    public String getToPages() 
    {
        return toPages;
    }
    public void setParam(String param) 
    {
        this.param = param;
    }

    public String getParam() 
    {
        return param;
    }
    public void setProcess(String process) 
    {
        this.process = process;
    }

    public String getProcess() 
    {
        return process;
    }
    public void setCoupon(String coupon) 
    {
        this.coupon = coupon;
    }

    public String getCoupon() 
    {
        return coupon;
    }
    public void setLinks(String links) 
    {
        this.links = links;
    }

    public String getLinks() 
    {
        return links;
    }
    public void setMark(String mark) 
    {
        this.mark = mark;
    }

    public String getMark() 
    {
        return mark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("configId", getConfigId())
            .append("configNum", getConfigNum())
            .append("configName", getConfigName())
            .append("banners", getBanners())
            .append("toPages", getToPages())
            .append("param", getParam())
            .append("process", getProcess())
            .append("coupon", getCoupon())
            .append("links", getLinks())
            .append("mark", getMark())
            .append("createTime", getCreateTime())
            .toString();
    }
}
