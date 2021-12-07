package com.ruoyi.recycle.service;

import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.FunUser;

import java.util.List;

/**
 * 用户信息Service接口
 *
 * @author ruoyi
 * @date 2021-11-05
 */
public interface IFunUserService {
    /**
     * 查询用户信息
     *
     * @param userID 用户信息主键
     * @return 用户信息
     */
    FunUser selectFunUserByUserID(Long userID);

    /**
     * 查询用户信息列表
     *
     * @param funUser 用户信息
     * @return 用户信息集合
     */
    List<FunUser> selectFunUserList(FunUser funUser);

    /**
     * 新增用户信息
     *
     * @param funUser 用户信息
     * @return 结果
     */
    int insertFunUser(FunUser funUser);

    /**
     * 修改用户信息
     *
     * @param funUser 用户信息
     * @return 结果
     */
    int updateFunUser(FunUser funUser);

    /**
     * 批量删除用户信息
     *
     * @param userIDs 需要删除的用户信息主键集合
     * @return 结果
     */
    int deleteFunUserByUserIDs(String userIDs);

    /**
     * 删除用户信息信息
     *
     * @param userID 用户信息主键
     * @return 结果
     */
    int deleteFunUserByUserID(Long userID);

    FunUser selectFunUserByUserNo(String userNo);

    void insertUserByRecycle(FunRecycle funRecycle);

    void updateUserByRecycle(FunRecycle funRecycle);
}
