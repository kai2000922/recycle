package com.ruoyi.recycle.service;

import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.express.SendOrderInfo;

import java.util.List;

/**
 * 回收订单Service接口
 *
 * @author ruoyi
 * @date 2021-10-07
 */
public interface IFunRecycleService {
    /**
     * 查询回收订单
     *
     * @param recycleID 回收订单主键
     * @return 回收订单
     */
    FunRecycle selectFunRecycleByRecycleID(Long recycleID);

    /**
     * 查询回收订单列表
     *
     * @param funRecycle 回收订单
     * @return 回收订单集合
     */
    List<FunRecycle> selectFunRecycleList(FunRecycle funRecycle);

    List<FunRecycle> selectQueryFunRecycleList(FunRecycle funRecycle);

    /**
     * 新增回收订单
     *
     * @param funRecycle 回收订单
     * @return 结果
     */
    int insertFunRecycle(FunRecycle funRecycle);

    /**
     * 修改回收订单
     *
     * @param funRecycle 回收订单
     * @return 结果
     */
    int updateFunRecycle(FunRecycle funRecycle);

    /**
     * 批量删除回收订单
     *
     * @param recycleIDs 需要删除的回收订单主键集合
     * @return 结果
     */
    int deleteFunRecycleByRecycleIDs(String recycleIDs);

    /**
     * 删除回收订单信息
     *
     * @param recycleID 回收订单主键
     * @return 结果
     */
    int deleteFunRecycleByRecycleID(Long recycleID);

    SendOrderInfo getOrder(FunRecycle funRecycle);

    String getOrderByChannel(String channel);

    FunRecycle selectFunRecycleByRecycleOrder(Long orderNum);

    String getExpressPrice(String offsetCity, String arriveCity);

    int getThisMonthOrders(String user);

    void sendRequestRegular();

    void queryOrderStatus();
}
