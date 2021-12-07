package com.ruoyi.recycle.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.domain.FunUser;
import com.ruoyi.recycle.service.IFunUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息Controller
 *
 * @author ruoyi
 * @date 2021-11-05
 */
@Controller
@RequestMapping("/recycle/user")
public class FunUserController extends BaseController {
    private final String prefix = "recycle/user";

    @Autowired
    private IFunUserService funUserService;

    @RequiresPermissions("recycle:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    /**
     * 查询用户信息列表
     */
//    @RequiresPermissions("recycle:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunUser funUser) {
        startPage();
        List<FunUser> list = funUserService.selectFunUserList(funUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @RequiresPermissions("recycle:user:export")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunUser funUser) {
        List<FunUser> list = funUserService.selectFunUserList(funUser);
        ExcelUtil<FunUser> util = new ExcelUtil<FunUser>(FunUser.class);
        return util.exportExcel(list, "用户信息数据");
    }

    /**
     * 新增用户信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户信息
     */
    @RequiresPermissions("recycle:user:add")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunUser funUser) {
        return toAjax(funUserService.insertFunUser(funUser));
    }

    /**
     * 修改用户信息
     */
    @GetMapping("/edit/{userID}")
    public String edit(@PathVariable("userID") Long userID, ModelMap mmap) {
        FunUser funUser = funUserService.selectFunUserByUserID(userID);
        mmap.put("funUser", funUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户信息
     */
    @RequiresPermissions("recycle:user:edit")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunUser funUser) {
        return toAjax(funUserService.updateFunUser(funUser));
    }

    /**
     * 删除用户信息
     */
    @RequiresPermissions("recycle:user:remove")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funUserService.deleteFunUserByUserIDs(ids));
    }

}
