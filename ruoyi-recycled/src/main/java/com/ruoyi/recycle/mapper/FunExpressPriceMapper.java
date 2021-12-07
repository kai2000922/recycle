package com.ruoyi.recycle.mapper;

import com.ruoyi.recycle.domain.FunExpressPrice;

import java.util.List;

/**
 * 物流价格Mapper接口
 *
 * @author ruoyi
 * @date 2021-11-04
 */
public interface FunExpressPriceMapper {
    /**
     * 查询物流价格
     *
     * @param outsetPlace 物流价格主键
     * @return 物流价格
     */
    FunExpressPrice selectFunExpressPriceByOutsetPlace(String outsetPlace);

    /**
     * 查询物流价格列表
     *
     * @param funExpressPrice 物流价格
     * @return 物流价格集合
     */
    List<FunExpressPrice> selectFunExpressPriceList(FunExpressPrice funExpressPrice);

    /**
     * 新增物流价格
     *
     * @param funExpressPrice 物流价格
     * @return 结果
     */
    int insertFunExpressPrice(FunExpressPrice funExpressPrice);

    /**
     * 修改物流价格
     *
     * @param funExpressPrice 物流价格
     * @return 结果
     */
    int updateFunExpressPrice(FunExpressPrice funExpressPrice);

    /**
     * 删除物流价格
     *
     * @param outsetPlace 物流价格主键
     * @return 结果
     */
    int deleteFunExpressPriceByOutsetPlace(String outsetPlace);

    /**
     * 批量删除物流价格
     *
     * @param outsetPlaces 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFunExpressPriceByOutsetPlaces(String[] outsetPlaces);
}
