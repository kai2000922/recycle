package com.ruoyi.recycle.service;

import com.ruoyi.recycle.domain.FunChannel;

import java.util.List;

/**
 * 渠道信息Service接口
 *
 * @author ruoyi
 * @date 2021-11-22
 */
public interface IFunChannelService {
    /**
     * 查询渠道信息
     *
     * @param channelId 渠道信息主键
     * @return 渠道信息
     */
    FunChannel selectFunChannelByChannelId(Long channelId);

    /**
     * 查询渠道信息列表
     *
     * @param funChannel 渠道信息
     * @return 渠道信息集合
     */
    List<FunChannel> selectFunChannelList(FunChannel funChannel);

    /**
     * 新增渠道信息
     *
     * @param funChannel 渠道信息
     * @return 结果
     */
    int insertFunChannel(FunChannel funChannel);

    /**
     * 修改渠道信息
     *
     * @param funChannel 渠道信息
     * @return 结果
     */
    int updateFunChannel(FunChannel funChannel);

    /**
     * 批量删除渠道信息
     *
     * @param channelIds 需要删除的渠道信息主键集合
     * @return 结果
     */
    int deleteFunChannelByChannelIds(String channelIds);

    /**
     * 删除渠道信息信息
     *
     * @param channelId 渠道信息主键
     * @return 结果
     */
    int deleteFunChannelByChannelId(Long channelId);
}
