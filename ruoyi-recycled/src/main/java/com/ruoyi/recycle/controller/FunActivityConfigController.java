package com.ruoyi.recycle.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.recycle.config.AliPayApiConfig;
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
import com.ruoyi.recycle.domain.FunActivityConfig;
import com.ruoyi.recycle.service.IFunActivityConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 活动页配置Controller
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
@Controller
@RequestMapping("/recycle/activityConfig")
public class FunActivityConfigController extends BaseController
{
    private String prefix = "recycle/activityConfig";

    @Autowired
    private IFunActivityConfigService funActivityConfigService;

    @RequiresPermissions("recycle:activityConfig:view")
    @GetMapping()
    public String activityConfig()
    {
        return prefix + "/activityConfig";
    }

    /**
     * 查询活动页配置列表
     */
//    @RequiresPermissions("recycle:activityConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunActivityConfig funActivityConfig)
    {
        startPage();
        List<FunActivityConfig> list = funActivityConfigService.selectFunActivityConfigList(funActivityConfig);
        return getDataTable(list);
    }

    /**
     * 导出活动页配置列表
     */
    @RequiresPermissions("recycle:activityConfig:export")
    @Log(title = "活动页配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunActivityConfig funActivityConfig)
    {
        List<FunActivityConfig> list = funActivityConfigService.selectFunActivityConfigList(funActivityConfig);
        ExcelUtil<FunActivityConfig> util = new ExcelUtil<FunActivityConfig>(FunActivityConfig.class);
        return util.exportExcel(list, "活动页配置数据");
    }

    /**
     * 新增活动页配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动页配置
     */
    @RequiresPermissions("recycle:activityConfig:add")
    @Log(title = "活动页配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MultipartFile banners_file, MultipartFile process_file, MultipartFile coupon_file, FunActivityConfig funActivityConfig) throws IOException {
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        // 上传并返回新文件名称
        String banners_file_name = FileUploadUtils.upload(filePath, banners_file);
        String process_file_name = FileUploadUtils.upload(filePath, process_file);
        String coupon_file_name = FileUploadUtils.upload(filePath, coupon_file);

        funActivityConfig.setBanners(banners_file_name);
        funActivityConfig.setProcess(process_file_name);
        funActivityConfig.setCoupon(coupon_file_name);

        funActivityConfig.setToPages(funActivityConfig.getToPages().replaceAll("%252F", "/"));

        String links = AliPayApiConfig.pro_pages + "?id=" + funActivityConfig.getConfigNum();
        funActivityConfig.setLinks(links);

        return toAjax(funActivityConfigService.insertFunActivityConfig(funActivityConfig));
    }

    /**
     * 修改活动页配置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap)
    {
        FunActivityConfig funActivityConfig = funActivityConfigService.selectFunActivityConfigByConfigId(configId);
        mmap.put("funActivityConfig", funActivityConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动页配置
     */
    @RequiresPermissions("recycle:activityConfig:edit")
    @Log(title = "活动页配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MultipartFile banners_file, MultipartFile process_file, MultipartFile coupon_file, FunActivityConfig funActivityConfig)
    {
        return toAjax(funActivityConfigService.updateFunActivityConfig(funActivityConfig));
    }

    /**
     * 删除活动页配置
     */
    @RequiresPermissions("recycle:activityConfig:remove")
    @Log(title = "活动页配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(funActivityConfigService.deleteFunActivityConfigByConfigIds(ids));
    }
}
