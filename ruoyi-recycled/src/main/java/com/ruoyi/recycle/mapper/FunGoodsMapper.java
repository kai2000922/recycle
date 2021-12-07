package com.ruoyi.recycle.mapper;

import com.ruoyi.recycle.domain.FunGoods;

import java.util.List;

/**
 * 商品信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-11-04
 */
public interface FunGoodsMapper {
    /**
     * 查询商品信息
     *
     * @param goodID 商品信息主键
     * @return 商品信息
     */
    FunGoods selectFunGoodsByGoodID(Integer goodID);

    /**
     * 查询商品信息列表
     *
     * @param funGoods 商品信息
     * @return 商品信息集合
     */
    List<FunGoods> selectFunGoodsList(FunGoods funGoods);

    /**
     * 新增商品信息
     *
     * @param funGoods 商品信息
     * @return 结果
     */
    int insertFunGoods(FunGoods funGoods);

    /**
     * 修改商品信息
     *
     * @param funGoods 商品信息
     * @return 结果
     */
    int updateFunGoods(FunGoods funGoods);

    /**
     * 删除商品信息
     *
     * @param goodID 商品信息主键
     * @return 结果
     */
    int deleteFunGoodsByGoodID(Integer goodID);

    /**
     * 批量删除商品信息
     *
     * @param goodIDs 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFunGoodsByGoodIDs(String[] goodIDs);
}
