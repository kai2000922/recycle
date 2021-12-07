package com.ruoyi.recycle.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.recycle.domain.FunExpressPrice;
import com.ruoyi.recycle.mapper.FunExpressPriceMapper;
import com.ruoyi.recycle.service.IFunExpressPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流价格Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-04
 */
@Service
public class FunExpressPriceServiceImpl implements IFunExpressPriceService {
    @Autowired
    private FunExpressPriceMapper funExpressPriceMapper;

    /**
     * 查询物流价格
     *
     * @param outsetPlace 物流价格主键
     * @return 物流价格
     */
    @Override
    public FunExpressPrice selectFunExpressPriceByOutsetPlace(String outsetPlace) {
        return funExpressPriceMapper.selectFunExpressPriceByOutsetPlace(outsetPlace);
    }

    /**
     * 查询物流价格列表
     *
     * @param funExpressPrice 物流价格
     * @return 物流价格
     */
    @Override
    public List<FunExpressPrice> selectFunExpressPriceList(FunExpressPrice funExpressPrice) {
        return funExpressPriceMapper.selectFunExpressPriceList(funExpressPrice);
    }

    /**
     * 新增物流价格
     *
     * @param funExpressPrice 物流价格
     * @return 结果
     */
    @Override
    public int insertFunExpressPrice(FunExpressPrice funExpressPrice) {
        return funExpressPriceMapper.insertFunExpressPrice(funExpressPrice);
    }

    /**
     * 修改物流价格
     *
     * @param funExpressPrice 物流价格
     * @return 结果
     */
    @Override
    public int updateFunExpressPrice(FunExpressPrice funExpressPrice) {
        return funExpressPriceMapper.updateFunExpressPrice(funExpressPrice);
    }

    /**
     * 批量删除物流价格
     *
     * @param outsetPlaces 需要删除的物流价格主键
     * @return 结果
     */
    @Override
    public int deleteFunExpressPriceByOutsetPlaces(String outsetPlaces) {
        return funExpressPriceMapper.deleteFunExpressPriceByOutsetPlaces(Convert.toStrArray(outsetPlaces));
    }

    /**
     * 删除物流价格信息
     *
     * @param outsetPlace 物流价格主键
     * @return 结果
     */
    @Override
    public int deleteFunExpressPriceByOutsetPlace(String outsetPlace) {
        return funExpressPriceMapper.deleteFunExpressPriceByOutsetPlace(outsetPlace);
    }
}
