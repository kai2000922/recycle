package com.ruoyi.recycle.mapper;

import com.ruoyi.recycle.domain.FunRecycle;

import java.util.List;

/**
 * 回收订单Mapper接口
 *
 * @author ruoyi
 * @date 2021-10-07
 */
public interface FunRecycleMapper {
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
     * 删除回收订单
     *
     * @param recycleID 回收订单主键
     * @return 结果
     */
    int deleteFunRecycleByRecycleID(Long recycleID);

    /**
     * 批量删除回收订单
     *
     * @param recycleIDs 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFunRecycleByRecycleIDs(String[] recycleIDs);

    FunRecycle selectFunRecycleByRecycleOrder(Long orderNum);
}
