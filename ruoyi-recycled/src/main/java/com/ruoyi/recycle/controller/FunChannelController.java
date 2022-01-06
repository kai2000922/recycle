package com.ruoyi.recycle.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.recycle.domain.FunChannel;
import com.ruoyi.recycle.service.IFunChannelService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.Channel;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 渠道信息Controller
 *
 * @author ruoyi
 * @date 2021-11-22
 */
@Controller
@RequestMapping("/recycle/channel")
public class FunChannelController extends BaseController {
    private final String prefix = "recycle/channel";
    public static final String preLinks = "alipays://platformapi/startapp?appId=2021002188669037&page=";

    @Autowired
    private IFunChannelService funChannelService;

    @RequiresPermissions("recycle:channel:view")
    @GetMapping()
    public String channel() {
        return prefix + "/channel";
    }

    /**
     * 查询渠道信息列表
     */
    @RequiresPermissions("recycle:channel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FunChannel funChannel) {
        Date begin = DateUtils.parseDate(funChannel.getParams().get("beginCreateTime"));
        if (begin != null) {
            begin.setHours(0);
            begin.setMinutes(0);
            Date end = DateUtils.parseDate(funChannel.getParams().get("endCreateTime"));
            end.setHours(23);
            end.setMinutes(59);
            funChannel.getParams().put("beginCreateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, begin));
            funChannel.getParams().put("endCreateTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, end));
        }
        List<FunChannel> list = funChannelService.selectFunChannelList(funChannel);
        HashSet<String> hashSet = new HashSet<>();
        HashMap<String, Integer> counters = new HashMap<>();
        HashMap<String, FunChannel> firstChannel = new HashMap<>();
        for (FunChannel channel : list) {
            if (!hashSet.contains(channel.getChannelName())) {
                counters.put(channel.getChannelName(), 1);
                firstChannel.put(channel.getChannelName(), channel);
            } else {
                Integer ints = counters.get(channel.getChannelName());
                counters.put(channel.getChannelName(), ++ints);
            }
            hashSet.add(channel.getChannelName());
        }

        list.clear();
        for (String string : hashSet) {
            FunChannel channel = firstChannel.get(string);
            channel.setCounter((long) counters.get(string));
            list.add(channel);
        }

        return getDataTable(list);
    }


    /**
     * 导出渠道信息列表
     */
    @RequiresPermissions("recycle:channel:export")
    @Log(title = "渠道信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FunChannel funChannel) {
        List<FunChannel> list = funChannelService.selectFunChannelList(funChannel);
        ExcelUtil<FunChannel> util = new ExcelUtil<FunChannel>(FunChannel.class);
        return util.exportExcel(list, "渠道信息数据");
    }

    /**
     * 新增渠道信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存渠道信息
     */
//    @RequiresPermissions("recycle:channel:add")
    @Log(title = "渠道信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FunChannel funChannel) {
        funChannel.setLinks(preLinks + funChannel.getPages() + "?channelName=" + funChannel.getChannelName());
        if (StringUtils.isNotEmpty(funChannel.getGoodsID())) {
            funChannel.setLinks(preLinks + funChannel.getPages() + "?goodsID=" + funChannel.getGoodsID() + "_" + funChannel.getChannelName());
        }
        return toAjax(funChannelService.insertFunChannel(funChannel));
    }

    /**
     * 修改渠道信息
     */
    @GetMapping("/edit/{channelId}")
    public String edit(@PathVariable("channelId") Long channelId, ModelMap mmap) {
        FunChannel funChannel = funChannelService.selectFunChannelByChannelId(channelId);
        mmap.put("funChannel", funChannel);
        return prefix + "/edit";
    }

    /**
     * 修改保存渠道信息
     */
    @RequiresPermissions("recycle:channel:edit")
    @Log(title = "渠道信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FunChannel funChannel) {
        return toAjax(funChannelService.updateFunChannel(funChannel));
    }

    /**
     * 删除渠道信息
     */
    @RequiresPermissions("recycle:channel:remove")
    @Log(title = "渠道信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(funChannelService.deleteFunChannelByChannelIds(ids));
    }
}
