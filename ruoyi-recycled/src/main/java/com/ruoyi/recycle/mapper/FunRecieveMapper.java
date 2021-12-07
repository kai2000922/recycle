package com.ruoyi.recycle.mapper;

import com.ruoyi.recycle.domain.FunRecieve;

import java.util.List;

/**
 * 收货地址信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-10-08
 */
public interface FunRecieveMapper {
    /**
     * 查询收货地址信息
     *
     * @param receiveID 收货地址信息主键
     * @return 收货地址信息
     */
    FunRecieve selectFunRecieveByReceiveID(Long receiveID);

    /**
     * 查询收货地址信息列表
     *
     * @param funRecieve 收货地址信息
     * @return 收货地址信息集合
     */
    List<FunRecieve> selectFunRecieveList(FunRecieve funRecieve);

    /**
     * 新增收货地址信息
     *
     * @param funRecieve 收货地址信息
     * @return 结果
     */
    int insertFunRecieve(FunRecieve funRecieve);

    /**
     * 修改收货地址信息
     *
     * @param funRecieve 收货地址信息
     * @return 结果
     */
    int updateFunRecieve(FunRecieve funRecieve);

    /**
     * 删除收货地址信息
     *
     * @param receiveID 收货地址信息主键
     * @return 结果
     */
    int deleteFunRecieveByReceiveID(Long receiveID);

    /**
     * 批量删除收货地址信息
     *
     * @param receiveIDs 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFunRecieveByReceiveIDs(String[] receiveIDs);

    List<FunRecieve> selectFunRecieveByProv(String prov);
}
