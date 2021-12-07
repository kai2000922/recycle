package com.ruoyi.recycle.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.domain.FunExpressPrice;
import com.ruoyi.recycle.service.IFunExpressPriceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物流价格Controller
 *
 * @author ruoyi
 * @date 2021-11-04
 */
@Controller
@RequestMapping("/recycle/expressPrice")
public class FunExpressPriceController extends BaseController {
    private final String prefix = "recycle/expressPrice";

    @Autowired
    private IFunExpressPriceService funExpressPriceService;

    @RequiresPermissions("recycle:expressPrice:view")
    @GetMapping()
    public String expressPrice() {
        return prefix + "/expressPrice";
    }

    /**
     * 查询物流价格列表
     */
    @RequiresPermissions("recycle:expressPrice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunExpressPrice funExpressPrice) {
        startPage();
        List<FunExpressPrice> list = funExpressPriceService.selectFunExpressPriceList(funExpressPrice);
        return getDataTable(list);
    }

    /**
     * 导出物流价格列表
     */
    @RequiresPermissions("recycle:expressPrice:export")
    @Log(title = "物流价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunExpressPrice funExpressPrice) {
        List<FunExpressPrice> list = funExpressPriceService.selectFunExpressPriceList(funExpressPrice);
        ExcelUtil<FunExpressPrice> util = new ExcelUtil<FunExpressPrice>(FunExpressPrice.class);
        return util.exportExcel(list, "物流价格数据");
    }

    /**
     * 新增物流价格
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存物流价格
     */
    @RequiresPermissions("recycle:expressPrice:add")
    @Log(title = "物流价格", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunExpressPrice funExpressPrice) {
        return toAjax(funExpressPriceService.insertFunExpressPrice(funExpressPrice));
    }

    /**
     * 修改物流价格
     */
    @GetMapping("/edit/{outsetPlace}")
    public String edit(@PathVariable("outsetPlace") String outsetPlace, ModelMap mmap) {
        FunExpressPrice funExpressPrice = funExpressPriceService.selectFunExpressPriceByOutsetPlace(outsetPlace);
        mmap.put("funExpressPrice", funExpressPrice);
        return prefix + "/edit";
    }

    /**
     * 修改保存物流价格
     */
    @RequiresPermissions("recycle:expressPrice:edit")
    @Log(title = "物流价格", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunExpressPrice funExpressPrice) {
        return toAjax(funExpressPriceService.updateFunExpressPrice(funExpressPrice));
    }

    /**
     * 删除物流价格
     */
    @RequiresPermissions("recycle:expressPrice:remove")
    @Log(title = "物流价格", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funExpressPriceService.deleteFunExpressPriceByOutsetPlaces(ids));
    }
}
