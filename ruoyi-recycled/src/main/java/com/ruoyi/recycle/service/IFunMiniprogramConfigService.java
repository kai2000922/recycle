package com.ruoyi.recycle.service;

import java.util.List;
import com.ruoyi.recycle.domain.FunMiniprogramConfig;

/**
 * 小程序配置Service接口
 * 
 * @author ruoyi
 * @date 2021-12-11
 */
public interface IFunMiniprogramConfigService 
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
     * 批量删除小程序配置
     * 
     * @param configIds 需要删除的小程序配置主键集合
     * @return 结果
     */
    public int deleteFunMiniprogramConfigByConfigIds(String configIds);

    /**
     * 删除小程序配置信息
     * 
     * @param configId 小程序配置主键
     * @return 结果
     */
    public int deleteFunMiniprogramConfigByConfigId(Long configId);
}
