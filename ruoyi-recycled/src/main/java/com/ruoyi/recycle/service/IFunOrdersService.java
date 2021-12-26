package com.ruoyi.recycle.service;

import com.ruoyi.recycle.domain.FunOrders;

import java.util.List;

/**
 * 商城订单Service接口
 *
 * @author ruoyi
 * @date 2021-11-03
 */
public interface IFunOrdersService {
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
     * 批量删除商城订单
     *
     * @param ordersIDs 需要删除的商城订单主键集合
     * @return 结果
     */
    int deleteFunOrdersByOrdersIDs(String ordersIDs);

    /**
     * 删除商城订单信息
     *
     * @param ordersID 商城订单主键
     * @return 结果
     */
    int deleteFunOrdersByOrdersID(Long ordersID);

    String importOrders(List<FunOrders> ordersList, Boolean isUpdateSupport);

    int updateOrdersDone(Integer ordersID);

    void queryOrderStatue();

}
