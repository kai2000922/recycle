package com.ruoyi.recycle.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recycle.mapper.FunMiniprogramConfigMapper;
import com.ruoyi.recycle.domain.FunMiniprogramConfig;
import com.ruoyi.recycle.service.IFunMiniprogramConfigService;
import com.ruoyi.common.core.text.Convert;

/**
 * 小程序配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-11
 */
@Service
public class FunMiniprogramConfigServiceImpl implements IFunMiniprogramConfigService 
{
    @Autowired
    private FunMiniprogramConfigMapper funMiniprogramConfigMapper;

    /**
     * 查询小程序配置
     * 
     * @param configId 小程序配置主键
     * @return 小程序配置
     */
    @Override
    public FunMiniprogramConfig selectFunMiniprogramConfigByConfigId(Long configId)
    {
        return funMiniprogramConfigMapper.selectFunMiniprogramConfigByConfigId(configId);
    }

    /**
     * 查询小程序配置列表
     * 
     * @param funMiniprogramConfig 小程序配置
     * @return 小程序配置
     */
    @Override
    public List<FunMiniprogramConfig> selectFunMiniprogramConfigList(FunMiniprogramConfig funMiniprogramConfig)
    {
        return funMiniprogramConfigMapper.selectFunMiniprogramConfigList(funMiniprogramConfig);
    }

    /**
     * 新增小程序配置
     * 
     * @param funMiniprogramConfig 小程序配置
     * @return 结果
     */
    @Override
    public int insertFunMiniprogramConfig(FunMiniprogramConfig funMiniprogramConfig)
    {
        funMiniprogramConfig.setCreateTime(DateUtils.getNowDate());
        return funMiniprogramConfigMapper.insertFunMiniprogramConfig(funMiniprogramConfig);
    }

    /**
     * 修改小程序配置
     * 
     * @param funMiniprogramConfig 小程序配置
     * @return 结果
     */
    @Override
    public int updateFunMiniprogramConfig(FunMiniprogramConfig funMiniprogramConfig)
    {
        return funMiniprogramConfigMapper.updateFunMiniprogramConfig(funMiniprogramConfig);
    }

    /**
     * 批量删除小程序配置
     * 
     * @param configIds 需要删除的小程序配置主键
     * @return 结果
     */
    @Override
    public int deleteFunMiniprogramConfigByConfigIds(String configIds)
    {
        return funMiniprogramConfigMapper.deleteFunMiniprogramConfigByConfigIds(Convert.toStrArray(configIds));
    }

    /**
     * 删除小程序配置信息
     * 
     * @param configId 小程序配置主键
     * @return 结果
     */
    @Override
    public int deleteFunMiniprogramConfigByConfigId(Long configId)
    {
        return funMiniprogramConfigMapper.deleteFunMiniprogramConfigByConfigId(configId);
    }
}
