package com.ruoyi.recycle.controller;

import java.util.List;

import com.alipay.api.response.AlipayMarketingCashlessvoucherTemplateCreateResponse;
import com.ruoyi.recycle.service.IAliPayService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.recycle.domain.FunCoupon;
import com.ruoyi.recycle.service.IFunCouponService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 优惠券配置Controller
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
@Controller
@RequestMapping("/recycle/coupon")
public class FunCouponController extends BaseController
{
    private String prefix = "recycle/coupon";

    @Autowired
    private IFunCouponService funCouponService;
    @Autowired
    private IAliPayService aliPayService;

    @RequiresPermissions("recycle:coupon:view")
    @GetMapping()
    public String coupon()
    {
        return prefix + "/coupon";
    }

    /**
     * 查询优惠券配置列表
     */
    @RequiresPermissions("recycle:coupon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunCoupon funCoupon)
    {
        startPage();
        List<FunCoupon> list = funCouponService.selectFunCouponList(funCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券配置列表
     */
    @RequiresPermissions("recycle:coupon:export")
    @Log(title = "优惠券配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunCoupon funCoupon)
    {
        List<FunCoupon> list = funCouponService.selectFunCouponList(funCoupon);
        ExcelUtil<FunCoupon> util = new ExcelUtil<FunCoupon>(FunCoupon.class);
        return util.exportExcel(list, "优惠券配置数据");
    }

    /**
     * 新增优惠券配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存优惠券配置
     */
    @RequiresPermissions("recycle:coupon:add")
    @Log(title = "优惠券配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunCoupon funCoupon)
    {
        AlipayMarketingCashlessvoucherTemplateCreateResponse response = aliPayService.createCoupon(funCoupon);
        funCoupon.setTemplates(response.getTemplateId());
        return toAjax(funCouponService.insertFunCoupon(funCoupon));
    }

    /**
     * 修改优惠券配置
     */
    @GetMapping("/edit/{couponId}")
    public String edit(@PathVariable("couponId") Long couponId, ModelMap mmap)
    {
        FunCoupon funCoupon = funCouponService.selectFunCouponByCouponId(couponId);
        mmap.put("funCoupon", funCoupon);
        return prefix + "/edit";
    }

    /**
     * 修改保存优惠券配置
     */
    @RequiresPermissions("recycle:coupon:edit")
    @Log(title = "优惠券配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunCoupon funCoupon)
    {
        return toAjax(funCouponService.updateFunCoupon(funCoupon));
    }

    /**
     * 删除优惠券配置
     */
    @RequiresPermissions("recycle:coupon:remove")
    @Log(title = "优惠券配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(funCouponService.deleteFunCouponByCouponIds(ids));
    }
}
