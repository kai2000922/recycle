package com.ruoyi.recycle.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
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
import com.ruoyi.recycle.domain.FunMiniprogramConfig;
import com.ruoyi.recycle.service.IFunMiniprogramConfigService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小程序配置Controller
 * 
 * @author ruoyi
 * @date 2021-12-11
 */
@Controller
@RequestMapping("/recycle/config")
public class FunMiniprogramConfigController extends BaseController
{
    private String prefix = "recycle/config";

    @Autowired
    private IFunMiniprogramConfigService funMiniprogramConfigService;

    @RequiresPermissions("recycle:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询小程序配置列表
     */
    @RequiresPermissions("recycle:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunMiniprogramConfig funMiniprogramConfig)
    {
        startPage();
        List<FunMiniprogramConfig> list = funMiniprogramConfigService.selectFunMiniprogramConfigList(funMiniprogramConfig);
        return getDataTable(list);
    }

    /**
     * 导出小程序配置列表
     */
    @RequiresPermissions("recycle:config:export")
    @Log(title = "小程序配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunMiniprogramConfig funMiniprogramConfig)
    {
        List<FunMiniprogramConfig> list = funMiniprogramConfigService.selectFunMiniprogramConfigList(funMiniprogramConfig);
        ExcelUtil<FunMiniprogramConfig> util = new ExcelUtil<FunMiniprogramConfig>(FunMiniprogramConfig.class);
        return util.exportExcel(list, "小程序配置数据");
    }

    /**
     * 新增小程序配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存小程序配置
     */
    @RequiresPermissions("recycle:config:add")
    @Log(title = "小程序配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunMiniprogramConfig funMiniprogramConfig)
    {
        return toAjax(funMiniprogramConfigService.insertFunMiniprogramConfig(funMiniprogramConfig));
    }

    /**
     * 修改小程序配置
     */
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap)
    {
        FunMiniprogramConfig funMiniprogramConfig = funMiniprogramConfigService.selectFunMiniprogramConfigByConfigId(configId);
        mmap.put("funMiniprogramConfig", funMiniprogramConfig);
        return prefix + "/edit";
    }

    /**
     * 修改保存小程序配置
     */
    @RequiresPermissions("recycle:config:edit")
    @Log(title = "小程序配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunMiniprogramConfig funMiniprogramConfig)
    {
        return toAjax(funMiniprogramConfigService.updateFunMiniprogramConfig(funMiniprogramConfig));
    }

    /**
     * 删除小程序配置
     */
    @RequiresPermissions("recycle:config:remove")
    @Log(title = "小程序配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(funMiniprogramConfigService.deleteFunMiniprogramConfigByConfigIds(ids));
    }

    @GetMapping("/getConfigs")
    @ResponseBody
    public AjaxResult getConfigs(){
        List<FunMiniprogramConfig> list = funMiniprogramConfigService.selectFunMiniprogramConfigList(null);
        JSONObject object = new JSONObject();
        List<FunMiniprogramConfig> banners = new ArrayList<>();
        List<FunMiniprogramConfig> stores = new ArrayList<>();
        List<FunMiniprogramConfig> activities = new ArrayList<>();


        for (FunMiniprogramConfig config : list){
            if (config.getConfigName().equals("Banner")){
                banners.add(config);
            }else if (config.getConfigName().equals("Store")){
                stores.add(config);
            }else if (config.getConfigName().equals("activity")){
                activities.add(config);
            }
            else {
                object.put(config.getConfigName(), config);
            }
        }
        object.put("banner", banners);
        object.put("store", stores);
        object.put("activity", activities);


        return AjaxResult.success(object.toJSONString());
    }

}
