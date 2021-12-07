package com.ruoyi.recycle.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.domain.FunRecieve;
import com.ruoyi.recycle.service.IFunRecieveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址信息Controller
 *
 * @author ruoyi
 * @date 2021-10-08
 */
@Controller
@RequestMapping("/recycle/recieve")
public class FunRecieveController extends BaseController {
    private final String prefix = "recycle/recieve";

    @Autowired
    private IFunRecieveService funRecieveService;

    @RequiresPermissions("recycle:recieve:view")
    @GetMapping()
    public String recieve() {
        return prefix + "/recieve";
    }

    /**
     * 查询收货地址信息列表
     */
    @RequiresPermissions("recycle:recieve:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunRecieve funRecieve) {
        startPage();
        List<FunRecieve> list = funRecieveService.selectFunRecieveList(funRecieve);
        return getDataTable(list);
    }

    /**
     * 导出收货地址信息列表
     */
    @RequiresPermissions("recycle:recieve:export")
    @Log(title = "收货地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunRecieve funRecieve) {
        List<FunRecieve> list = funRecieveService.selectFunRecieveList(funRecieve);
        ExcelUtil<FunRecieve> util = new ExcelUtil<FunRecieve>(FunRecieve.class);
        return util.exportExcel(list, "收货地址信息数据");
    }

    /**
     * 新增收货地址信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存收货地址信息
     */
    @RequiresPermissions("recycle:recieve:add")
    @Log(title = "收货地址信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunRecieve funRecieve) {
        return toAjax(funRecieveService.insertFunRecieve(funRecieve));
    }

    /**
     * 修改收货地址信息
     */
    @GetMapping("/edit/{receiveID}")
    public String edit(@PathVariable("receiveID") Long receiveID, ModelMap mmap) {
        FunRecieve funRecieve = funRecieveService.selectFunRecieveByReceiveID(receiveID);
        mmap.put("funRecieve", funRecieve);
        return prefix + "/edit";
    }

    /**
     * 修改保存收货地址信息
     */
    @RequiresPermissions("recycle:recieve:edit")
    @Log(title = "收货地址信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunRecieve funRecieve) {
        return toAjax(funRecieveService.updateFunRecieve(funRecieve));
    }

    /**
     * 删除收货地址信息
     */
    @RequiresPermissions("recycle:recieve:remove")
    @Log(title = "收货地址信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funRecieveService.deleteFunRecieveByReceiveIDs(ids));
    }

}
