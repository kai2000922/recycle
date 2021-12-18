package com.ruoyi.recycle.mapper;

import java.util.List;
import com.ruoyi.recycle.domain.FunActivityConfig;

/**
 * 活动页配置Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-17
 */
public interface FunActivityConfigMapper 
{
    /**
     * 查询活动页配置
     * 
     * @param configId 活动页配置主键
     * @return 活动页配置
     */
    public FunActivityConfig selectFunActivityConfigByConfigId(Long configId);

    /**
     * 查询活动页配置列表
     * 
     * @param funActivityConfig 活动页配置
     * @return 活动页配置集合
     */
    public List<FunActivityConfig> selectFunActivityConfigList(FunActivityConfig funActivityConfig);

    /**
     * 新增活动页配置
     * 
     * @param funActivityConfig 活动页配置
     * @return 结果
     */
    public int insertFunActivityConfig(FunActivityConfig funActivityConfig);

    /**
     * 修改活动页配置
     * 
     * @param funActivityConfig 活动页配置
     * @return 结果
     */
    public int updateFunActivityConfig(FunActivityConfig funActivityConfig);

    /**
     * 删除活动页配置
     * 
     * @param configId 活动页配置主键
     * @return 结果
     */
    public int deleteFunActivityConfigByConfigId(Long configId);

    /**
     * 批量删除活动页配置
     * 
     * @param configIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFunActivityConfigByConfigIds(String[] configIds);
}
