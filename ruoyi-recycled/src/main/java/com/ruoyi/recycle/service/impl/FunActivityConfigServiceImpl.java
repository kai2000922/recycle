package com.ruoyi.recycle.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recycle.mapper.FunActivityConfigMapper;
import com.ruoyi.recycle.domain.FunActivityConfig;
import com.ruoyi.recycle.service.IFunActivityConfigService;
import com.ruoyi.common.core.text.Convert;

/**
 * 活动页配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
@Service
public class FunActivityConfigServiceImpl implements IFunActivityConfigService 
{
    @Autowired
    private FunActivityConfigMapper funActivityConfigMapper;

    /**
     * 查询活动页配置
     * 
     * @param configId 活动页配置主键
     * @return 活动页配置
     */
    @Override
    public FunActivityConfig selectFunActivityConfigByConfigId(Long configId)
    {
        return funActivityConfigMapper.selectFunActivityConfigByConfigId(configId);
    }

    /**
     * 查询活动页配置列表
     * 
     * @param funActivityConfig 活动页配置
     * @return 活动页配置
     */
    @Override
    public List<FunActivityConfig> selectFunActivityConfigList(FunActivityConfig funActivityConfig)
    {
        return funActivityConfigMapper.selectFunActivityConfigList(funActivityConfig);
    }

    /**
     * 新增活动页配置
     * 
     * @param funActivityConfig 活动页配置
     * @return 结果
     */
    @Override
    public int insertFunActivityConfig(FunActivityConfig funActivityConfig)
    {
        funActivityConfig.setCreateTime(DateUtils.getNowDate());
        return funActivityConfigMapper.insertFunActivityConfig(funActivityConfig);
    }

    /**
     * 修改活动页配置
     * 
     * @param funActivityConfig 活动页配置
     * @return 结果
     */
    @Override
    public int updateFunActivityConfig(FunActivityConfig funActivityConfig)
    {
        return funActivityConfigMapper.updateFunActivityConfig(funActivityConfig);
    }

    /**
     * 批量删除活动页配置
     * 
     * @param configIds 需要删除的活动页配置主键
     * @return 结果
     */
    @Override
    public int deleteFunActivityConfigByConfigIds(String configIds)
    {
        return funActivityConfigMapper.deleteFunActivityConfigByConfigIds(Convert.toStrArray(configIds));
    }

    /**
     * 删除活动页配置信息
     * 
     * @param configId 活动页配置主键
     * @return 结果
     */
    @Override
    public int deleteFunActivityConfigByConfigId(Long configId)
    {
        return funActivityConfigMapper.deleteFunActivityConfigByConfigId(configId);
    }
}
