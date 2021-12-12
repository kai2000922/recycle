package com.ruoyi.recycle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序配置对象 fun_miniprogram_config
 * 
 * @author ruoyi
 * @date 2021-12-11
 */
public class FunMiniprogramConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 配置ID */
    private Long configId;

    /** 配置名称 */
    @Excel(name = "配置名称")
    private String configName;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String configImgPath;

    /** 界面跳转 */
    @Excel(name = "界面跳转")
    private String toPages;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long goodsId;

    public void setConfigId(Long configId) 
    {
        this.configId = configId;
    }

    public Long getConfigId() 
    {
        return configId;
    }
    public void setConfigName(String configName) 
    {
        this.configName = configName;
    }

    public String getConfigName() 
    {
        return configName;
    }
    public void setConfigImgPath(String configImgPath) 
    {
        this.configImgPath = configImgPath;
    }

    public String getConfigImgPath() 
    {
        return configImgPath;
    }
    public void setToPages(String toPages) 
    {
        this.toPages = toPages;
    }

    public String getToPages() 
    {
        return toPages;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("configId", getConfigId())
            .append("configName", getConfigName())
            .append("configImgPath", getConfigImgPath())
            .append("toPages", getToPages())
            .append("goodsId", getGoodsId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
