package com.ruoyi.recycle.mapper;

import java.util.List;
import com.ruoyi.recycle.domain.FunCoupon;

/**
 * 优惠券配置Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
public interface FunCouponMapper 
{
    /**
     * 查询优惠券配置
     * 
     * @param couponId 优惠券配置主键
     * @return 优惠券配置
     */
    public FunCoupon selectFunCouponByCouponId(Long couponId);

    /**
     * 查询优惠券配置列表
     * 
     * @param funCoupon 优惠券配置
     * @return 优惠券配置集合
     */
    public List<FunCoupon> selectFunCouponList(FunCoupon funCoupon);

    /**
     * 新增优惠券配置
     * 
     * @param funCoupon 优惠券配置
     * @return 结果
     */
    public int insertFunCoupon(FunCoupon funCoupon);

    /**
     * 修改优惠券配置
     * 
     * @param funCoupon 优惠券配置
     * @return 结果
     */
    public int updateFunCoupon(FunCoupon funCoupon);

    /**
     * 删除优惠券配置
     * 
     * @param couponId 优惠券配置主键
     * @return 结果
     */
    public int deleteFunCouponByCouponId(Long couponId);

    /**
     * 批量删除优惠券配置
     * 
     * @param couponIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFunCouponByCouponIds(String[] couponIds);
}
