package com.ruoyi.recycle.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.recycle.domain.FunChannel;
import com.ruoyi.recycle.mapper.FunChannelMapper;
import com.ruoyi.recycle.service.IFunChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 渠道信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-22
 */
@Service
public class FunChannelServiceImpl implements IFunChannelService {
    @Autowired
    private FunChannelMapper funChannelMapper;

    /**
     * 查询渠道信息
     *
     * @param channelId 渠道信息主键
     * @return 渠道信息
     */
    @Override
    public FunChannel selectFunChannelByChannelId(Long channelId) {
        return funChannelMapper.selectFunChannelByChannelId(channelId);
    }

    /**
     * 查询渠道信息列表
     *
     * @param funChannel 渠道信息
     * @return 渠道信息
     */
    @Override
    public List<FunChannel> selectFunChannelList(FunChannel funChannel) {
        return funChannelMapper.selectFunChannelList(funChannel);
    }

    /**
     * 新增渠道信息
     *
     * @param funChannel 渠道信息
     * @return 结果
     */
    @Override
    public int insertFunChannel(FunChannel funChannel) {
        funChannel.setCreateTime(DateUtils.getNowDate());
        return funChannelMapper.insertFunChannel(funChannel);
    }

    /**
     * 修改渠道信息
     *
     * @param funChannel 渠道信息
     * @return 结果
     */
    @Override
    public int updateFunChannel(FunChannel funChannel) {
        funChannel.setUpdateTime(DateUtils.getNowDate());
        return funChannelMapper.updateFunChannel(funChannel);
    }

    /**
     * 批量删除渠道信息
     *
     * @param channelIds 需要删除的渠道信息主键
     * @return 结果
     */
    @Override
    public int deleteFunChannelByChannelIds(String channelIds) {
        return funChannelMapper.deleteFunChannelByChannelIds(Convert.toStrArray(channelIds));
    }

    /**
     * 删除渠道信息信息
     *
     * @param channelId 渠道信息主键
     * @return 结果
     */
    @Override
    public int deleteFunChannelByChannelId(Long channelId) {
        return funChannelMapper.deleteFunChannelByChannelId(channelId);
    }
}
