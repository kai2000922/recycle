package com.ruoyi.recycle.mapper;

import java.util.List;
import com.ruoyi.recycle.domain.FunMiniprogramConfig;

/**
 * 小程序配置Mapper接口
 * 
 * @author ruoyi
 * @date 2021-12-11
 */
public interface FunMiniprogramConfigMapper 
{
    /**
     * 查询小程序配置
     * 
     * @param configId 小程序配置主键
     * @return 小程序配置
     */
    public FunMiniprogramConfig selectFunMiniprogramConfigByConfigId(Long configId);

    /**
     * 查询小程序配置列表
     * 
     * @param funMiniprogramConfig 小程序配置
     * @return 小程序配置集合
     */
    public List<FunMiniprogramConfig> selectFunMiniprogramConfigList(FunMiniprogramConfig funMiniprogramConfig);

    /**
     * 新增小程序配置
     * 
     * @param funMiniprogramConfig 小程序配置
     * @return 结果
     */
    public int insertFunMiniprogramConfig(FunMiniprogramConfig funMiniprogramConfig);

    /**
     * 修改小程序配置
     * 
     * @param funMiniprogramConfig 小程序配置
     * @return 结果
     */
    public int updateFunMiniprogramConfig(FunMiniprogramConfig funMiniprogramConfig);

    /**
     * 删除小程序配置
     * 
     * @param configId 小程序配置主键
     * @return 结果
     */
    public int deleteFunMiniprogramConfigByConfigId(Long configId);

    /**
     * 批量删除小程序配置
     * 
     * @param configIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFunMiniprogramConfigByConfigIds(String[] configIds);
}
