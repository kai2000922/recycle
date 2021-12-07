package com.ruoyi.recycle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品信息对象 fun_goods
 *
 * @author ruoyi
 * @date 2021-11-04
 */
public class FunGoods extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Integer goodID;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 商品描述
     */
    @Excel(name = "商品描述")
    private String goodsDescribe;

    /**
     * 商品参数
     */
    @Excel(name = "商品参数")
    private String goodsType;

    /**
     * 划线价
     */
    @Excel(name = "划线价")
    private Double hxPrice;

    /**
     * 优惠价
     */
    @Excel(name = "优惠价")
    private Double yhPrice;

    /**
     * 支付价
     */
    @Excel(name = "支付价")
    private Double zfPrice;

    /**
     * 运费
     */
    @Excel(name = "运费")
    private Double expressPrice;

    /**
     * 轮播图
     */
    @Excel(name = "轮播图")
    private String images;

    /**
     * 详情图
     */
    @Excel(name = "详情图")
    private String descImages;

    /**
     * 订单图
     */
    @Excel(name = "订单图")
    private String orderImages;

    /**
     * 渠道来源
     */
    @Excel(name = "渠道来源")
    private String channel;

    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private String goodsNum;

    /**
     * 导出名称
     */
    @Excel(name = "导出名称")
    private String exportName;

    /**
     * 商品状态
     */
    @Excel(name = "商品状态")
    private String goodsStatus;

    public void setGoodID(Integer goodID) {
        this.goodID = goodID;
    }

    public Integer getGoodID() {
        return goodID;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setHxPrice(Double hxPrice) {
        this.hxPrice = hxPrice;
    }

    public Double getHxPrice() {
        return hxPrice;
    }

    public void setYhPrice(Double yhPrice) {
        this.yhPrice = yhPrice;
    }

    public Double getYhPrice() {
        return yhPrice;
    }

    public void setZfPrice(Double zfPrice) {
        this.zfPrice = zfPrice;
    }

    public Double getZfPrice() {
        return zfPrice;
    }

    public void setExpressPrice(Double expressPrice) {
        this.expressPrice = expressPrice;
    }

    public Double getExpressPrice() {
        return expressPrice;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setDescImages(String descImages) {
        this.descImages = descImages;
    }

    public String getDescImages() {
        return descImages;
    }

    public void setOrderImages(String orderImages) {
        this.orderImages = orderImages;
    }

    public String getOrderImages() {
        return orderImages;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public String getExportName() {
        return exportName;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("goodID", getGoodID())
                .append("goodsName", getGoodsName())
                .append("goodsDescribe", getGoodsDescribe())
                .append("goodsType", getGoodsType())
                .append("hxPrice", getHxPrice())
                .append("yhPrice", getYhPrice())
                .append("zfPrice", getZfPrice())
                .append("expressPrice", getExpressPrice())
                .append("images", getImages())
                .append("descImages", getDescImages())
                .append("orderImages", getOrderImages())
                .append("channel", getChannel())
                .append("goodsNum", getGoodsNum())
                .append("exportName", getExportName())
                .append("goodsStatus", getGoodsStatus())
                .toString();
    }
}
