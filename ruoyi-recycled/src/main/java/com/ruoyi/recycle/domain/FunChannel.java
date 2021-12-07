package com.ruoyi.recycle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 渠道信息对象 fun_channel
 *
 * @author ruoyi
 * @date 2021-11-22
 */
public class FunChannel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 渠道ID
     */
    private Long channelId;

    /**
     * 渠道名称
     */
    @Excel(name = "渠道名称")
    private String channelName;

    /**
     * 计数
     */
    @Excel(name = "计数")
    private Long counter;

    /**
     * 链接
     */
    @Excel(name = "链接")
    private String links;

    /**
     * 页面
     */
    @Excel(name = "页面")
    private String pages;

    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    private String goodsID;

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Long getCounter() {
        return counter;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getLinks() {
        return links;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsID() {
        return goodsID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("channelId", getChannelId())
                .append("channelName", getChannelName())
                .append("counter", getCounter())
                .append("links", getLinks())
                .append("pages", getPages())
                .append("createTime", getCreateTime())
                .append("remark", getRemark())
                .append("goodsID", getGoodsID())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
