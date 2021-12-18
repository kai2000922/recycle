package com.ruoyi.recycle.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recycle.mapper.FunCouponMapper;
import com.ruoyi.recycle.domain.FunCoupon;
import com.ruoyi.recycle.service.IFunCouponService;
import com.ruoyi.common.core.text.Convert;

/**
 * 优惠券配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
@Service
public class FunCouponServiceImpl implements IFunCouponService 
{
    @Autowired
    private FunCouponMapper funCouponMapper;

    /**
     * 查询优惠券配置
     * 
     * @param couponId 优惠券配置主键
     * @return 优惠券配置
     */
    @Override
    public FunCoupon selectFunCouponByCouponId(Long couponId)
    {
        return funCouponMapper.selectFunCouponByCouponId(couponId);
    }

    /**
     * 查询优惠券配置列表
     * 
     * @param funCoupon 优惠券配置
     * @return 优惠券配置
     */
    @Override
    public List<FunCoupon> selectFunCouponList(FunCoupon funCoupon)
    {
        return funCouponMapper.selectFunCouponList(funCoupon);
    }

    /**
     * 新增优惠券配置
     * 
     * @param funCoupon 优惠券配置
     * @return 结果
     */
    @Override
    public int insertFunCoupon(FunCoupon funCoupon)
    {
        funCoupon.setCreateTime(DateUtils.getNowDate());
        return funCouponMapper.insertFunCoupon(funCoupon);
    }

    /**
     * 修改优惠券配置
     * 
     * @param funCoupon 优惠券配置
     * @return 结果
     */
    @Override
    public int updateFunCoupon(FunCoupon funCoupon)
    {
        return funCouponMapper.updateFunCoupon(funCoupon);
    }

    /**
     * 批量删除优惠券配置
     * 
     * @param couponIds 需要删除的优惠券配置主键
     * @return 结果
     */
    @Override
    public int deleteFunCouponByCouponIds(String couponIds)
    {
        return funCouponMapper.deleteFunCouponByCouponIds(Convert.toStrArray(couponIds));
    }

    /**
     * 删除优惠券配置信息
     * 
     * @param couponId 优惠券配置主键
     * @return 结果
     */
    @Override
    public int deleteFunCouponByCouponId(Long couponId)
    {
        return funCouponMapper.deleteFunCouponByCouponId(couponId);
    }
}
