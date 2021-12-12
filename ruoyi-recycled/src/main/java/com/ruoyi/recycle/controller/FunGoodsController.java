package com.ruoyi.recycle.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.domain.FunGoods;
import com.ruoyi.recycle.service.IFunGoodsService;
import com.ruoyi.system.domain.SysFileInfo;
import com.ruoyi.system.service.ISysFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息Controller
 *
 * @author ruoyi
 * @date 2021-11-04
 */
@Controller
@RequestMapping("/recycle/goods")
@Api("商品信息")
public class FunGoodsController extends BaseController {
    private final String prefix = "recycle/goods";

    @Autowired
    private IFunGoodsService funGoodsService;
    @Autowired
    private ISysFileInfoService fileInfoService;

    @RequiresPermissions("recycle:goods:view")
    @GetMapping()
    public String goods() {
        return prefix + "/goods";
    }

    /**
     * 查询商品信息列表
     */
//    @RequiresPermissions("recycle:goods:list")
    @PostMapping("/list")
    @ResponseBody
    @ApiOperation("商品列表")
    @ApiImplicitParam(name = "funGoods", dataTypeClass = FunGoods.class)
    public TableDataInfo list(FunGoods funGoods) {
        startPage();
        List<FunGoods> list = funGoodsService.selectFunGoodsList(funGoods);
        return getDataTable(list);
    }

    @PostMapping("/listByID")
    @ResponseBody
    public AjaxResult listByID(long goodsID) {
        FunGoods funGoods = funGoodsService.selectFunGoodsByGoodID((int) goodsID);
        if (funGoods == null) {
            return AjaxResult.error("商品不存在");
        } else {
            return AjaxResult.success(funGoods);
        }
    }

    /**
     * 导出商品信息列表
     */
    @RequiresPermissions("recycle:goods:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunGoods funGoods) {
        List<FunGoods> list = funGoodsService.selectFunGoodsList(funGoods);
        ExcelUtil<FunGoods> util = new ExcelUtil<FunGoods>(FunGoods.class);
        return util.exportExcel(list, "商品信息数据");
    }

    /**
     * 新增商品信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商品信息
     */
    @RequiresPermissions("recycle:goods:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunGoods funGoods) {
        return toAjax(funGoodsService.insertFunGoods(funGoods));
    }

    /**
     * 修改商品信息
     */
    @GetMapping("/edit/{goodID}")
    public String edit(@PathVariable("goodID") Integer goodID, ModelMap mmap) {
        FunGoods funGoods = funGoodsService.selectFunGoodsByGoodID(goodID);
        mmap.put("funGoods", funGoods);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品信息
     */
    @RequiresPermissions("recycle:goods:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunGoods funGoods) {
        return toAjax(funGoodsService.updateFunGoods(funGoods));
    }

    /**
     * 删除商品信息
     */
    @RequiresPermissions("recycle:goods:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funGoodsService.deleteFunGoodsByGoodIDs(ids));
    }

    //获取首页Banner
    @GetMapping("/getBanner")
    @ResponseBody
    public AjaxResult getBanned() {
        SysFileInfo sysFileInfo = new SysFileInfo();
        sysFileInfo.setFileName("Banner");
        try {
            return AjaxResult.success(fileInfoService.selectSysFileInfoList(sysFileInfo));
        } catch (Exception e) {
            return AjaxResult.error("获取首页失败！");
        }
    }

    //获取商城页Banner
    @GetMapping("/getStore")
    @ResponseBody
    public AjaxResult getStore() {
        SysFileInfo sysFileInfo = new SysFileInfo();
        sysFileInfo.setFileName("Store");
        try {
            return AjaxResult.success(fileInfoService.selectSysFileInfoList(sysFileInfo));
        } catch (Exception e) {
            return AjaxResult.error("获取首页失败！");
        }
    }

    //获取传送带接口
    @GetMapping("/getStoreList")
    @ResponseBody
    public AjaxResult getStoreList() {
        List<String> list = new ArrayList<>();
        FunGoods temp = new FunGoods();
        temp.setChannel("1");
        List<FunGoods> goodsList = funGoodsService.selectFunGoodsList(temp);
        for (FunGoods goods : goodsList) {
            JSONObject object = new JSONObject();
            object.put("img", goods.getOrderImages());
            object.put("id", goods.getGoodID());
            list.add(object.toJSONString());
        }
        return AjaxResult.success(list);
    }
}
