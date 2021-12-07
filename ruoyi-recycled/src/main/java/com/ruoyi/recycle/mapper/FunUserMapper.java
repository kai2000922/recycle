package com.ruoyi.recycle.mapper;

import com.ruoyi.recycle.domain.FunUser;

import java.util.List;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-11-05
 */
public interface FunUserMapper {
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
     * 删除用户信息
     *
     * @param userID 用户信息主键
     * @return 结果
     */
    int deleteFunUserByUserID(Long userID);

    /**
     * 批量删除用户信息
     *
     * @param userIDs 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFunUserByUserIDs(String[] userIDs);

    FunUser selectFunUserByUserNo(String userNo);

}
