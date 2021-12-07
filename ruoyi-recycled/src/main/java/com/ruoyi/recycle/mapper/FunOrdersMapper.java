package com.ruoyi.recycle.mapper;

import com.ruoyi.recycle.domain.FunOrders;

import java.util.List;

/**
 * 商城订单Mapper接口
 *
 * @author ruoyi
 * @date 2021-11-04
 */
public interface FunOrdersMapper {
    /**
     * 查询商城订单
     *
     * @param ordersID 商城订单主键
     * @return 商城订单
     */
    FunOrders selectFunOrdersByOrdersID(Long ordersID);

    /**
     * 查询商城订单列表
     *
     * @param funOrders 商城订单
     * @return 商城订单集合
     */
    List<FunOrders> selectFunOrdersList(FunOrders funOrders);

    /**
     * 新增商城订单
     *
     * @param funOrders 商城订单
     * @return 结果
     */
    int insertFunOrders(FunOrders funOrders);

    /**
     * 修改商城订单
     *
     * @param funOrders 商城订单
     * @return 结果
     */
    int updateFunOrders(FunOrders funOrders);

    /**
     * 删除商城订单
     *
     * @param ordersID 商城订单主键
     * @return 结果
     */
    int deleteFunOrdersByOrdersID(Long ordersID);

    /**
     * 批量删除商城订单
     *
     * @param ordersIDs 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFunOrdersByOrdersIDs(String[] ordersIDs);
}
