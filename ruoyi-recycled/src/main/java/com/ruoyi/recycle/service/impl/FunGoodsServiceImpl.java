package com.ruoyi.recycle.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.recycle.domain.FunGoods;
import com.ruoyi.recycle.mapper.FunGoodsMapper;
import com.ruoyi.recycle.service.IFunGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-04
 */
@Service
public class FunGoodsServiceImpl implements IFunGoodsService {
    @Autowired
    private FunGoodsMapper funGoodsMapper;

    /**
     * 查询商品信息
     *
     * @param goodID 商品信息主键
     * @return 商品信息
     */
    @Override
    public FunGoods selectFunGoodsByGoodID(Integer goodID) {
        return funGoodsMapper.selectFunGoodsByGoodID(goodID);
    }

    /**
     * 查询商品信息列表
     *
     * @param funGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<FunGoods> selectFunGoodsList(FunGoods funGoods) {
        return funGoodsMapper.selectFunGoodsList(funGoods);
    }

    /**
     * 新增商品信息
     *
     * @param funGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertFunGoods(FunGoods funGoods) {
        return funGoodsMapper.insertFunGoods(funGoods);
    }

    /**
     * 修改商品信息
     *
     * @param funGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateFunGoods(FunGoods funGoods) {
        return funGoodsMapper.updateFunGoods(funGoods);
    }

    /**
     * 批量删除商品信息
     *
     * @param goodIDs 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteFunGoodsByGoodIDs(String goodIDs) {
        return funGoodsMapper.deleteFunGoodsByGoodIDs(Convert.toStrArray(goodIDs));
    }

    /**
     * 删除商品信息信息
     *
     * @param goodID 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteFunGoodsByGoodID(Integer goodID) {
        return funGoodsMapper.deleteFunGoodsByGoodID(goodID);
    }
}
