package com.ruoyi.recycle.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.recycle.domain.FunRecieve;
import com.ruoyi.recycle.mapper.FunRecieveMapper;
import com.ruoyi.recycle.service.IFunRecieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-10-08
 */
@Service
public class FunRecieveServiceImpl implements IFunRecieveService {
    @Autowired
    private FunRecieveMapper funRecieveMapper;

    /**
     * 查询收货地址信息
     *
     * @param receiveID 收货地址信息主键
     * @return 收货地址信息
     */
    @Override
    public FunRecieve selectFunRecieveByReceiveID(Long receiveID) {
        return funRecieveMapper.selectFunRecieveByReceiveID(receiveID);
    }

    /**
     * 查询收货地址信息列表
     *
     * @param funRecieve 收货地址信息
     * @return 收货地址信息
     */
    @Override
    public List<FunRecieve> selectFunRecieveList(FunRecieve funRecieve) {
        return funRecieveMapper.selectFunRecieveList(funRecieve);
    }

    /**
     * 新增收货地址信息
     *
     * @param funRecieve 收货地址信息
     * @return 结果
     */
    @Override
    public int insertFunRecieve(FunRecieve funRecieve) {
        return funRecieveMapper.insertFunRecieve(funRecieve);
    }

    /**
     * 修改收货地址信息
     *
     * @param funRecieve 收货地址信息
     * @return 结果
     */
    @Override
    public int updateFunRecieve(FunRecieve funRecieve) {
        return funRecieveMapper.updateFunRecieve(funRecieve);
    }

    /**
     * 批量删除收货地址信息
     *
     * @param receiveIDs 需要删除的收货地址信息主键
     * @return 结果
     */
    @Override
    public int deleteFunRecieveByReceiveIDs(String receiveIDs) {
        return funRecieveMapper.deleteFunRecieveByReceiveIDs(Convert.toStrArray(receiveIDs));
    }

    /**
     * 删除收货地址信息信息
     *
     * @param receiveID 收货地址信息主键
     * @return 结果
     */
    @Override
    public int deleteFunRecieveByReceiveID(Long receiveID) {
        return funRecieveMapper.deleteFunRecieveByReceiveID(receiveID);
    }

    @Override
    public List<FunRecieve> selectFunRecieveByProv(String prov) {
        return funRecieveMapper.selectFunRecieveByProv(prov);
    }

}
