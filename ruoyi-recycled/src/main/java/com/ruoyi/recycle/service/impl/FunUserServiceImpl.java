package com.ruoyi.recycle.service.impl;

import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.FunUser;
import com.ruoyi.recycle.mapper.FunUserMapper;
import com.ruoyi.recycle.service.IFunUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-05
 */
@Service
public class FunUserServiceImpl implements IFunUserService {
    @Resource
    private FunUserMapper funUserMapper;

    private static final Logger log = LoggerFactory.getLogger(FunUserServiceImpl.class);


    /**
     * 查询用户信息
     *
     * @param userID 用户信息主键
     * @return 用户信息
     */
    @Override
    public FunUser selectFunUserByUserID(Long userID) {
        return funUserMapper.selectFunUserByUserID(userID);
    }

    /**
     * 查询用户信息列表
     *
     * @param funUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<FunUser> selectFunUserList(FunUser funUser) {
        return funUserMapper.selectFunUserList(funUser);
    }

    /**
     * 新增用户信息
     *
     * @param funUser 用户信息
     * @return 结果
     */
    @Override
    public int insertFunUser(FunUser funUser) {
        funUser.setCreateTime(DateUtils.getNowDate());
        return funUserMapper.insertFunUser(funUser);
    }

    /**
     * 修改用户信息
     *
     * @param funUser 用户信息
     * @return 结果
     */
    @Override
    public int updateFunUser(FunUser funUser) {
        funUser.setUpdateTime(DateUtils.getNowDate());
        return funUserMapper.updateFunUser(funUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIDs 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteFunUserByUserIDs(String userIDs) {
        return funUserMapper.deleteFunUserByUserIDs(Convert.toStrArray(userIDs));
    }

    /**
     * 删除用户信息信息
     *
     * @param userID 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteFunUserByUserID(Long userID) {
        return funUserMapper.deleteFunUserByUserID(userID);
    }

    @Override
    public FunUser selectFunUserByUserNo(String userNo) {
        return funUserMapper.selectFunUserByUserNo(userNo);
    }

    @Override
    public void insertUserByRecycle(FunRecycle funRecycle) {
        FunUser funUser = funUserMapper.selectFunUserByUserNo(funRecycle.getUser());
        try {
            funUser = new FunUser();
            funUser.setUserNo(funRecycle.getUser());
            funUser.setCreateTime(new Date());
            funUser.setUserName(funRecycle.getName());
            funUser.setUserPhone(funRecycle.getPhone());
            funUser.setRecentAdd(funRecycle.getAddress());
            FunUser temp = funUserMapper.selectFunUserByUserNo(funRecycle.getUser());
            if (temp == null || temp.getUserID() == null) {
                funUserMapper.insertFunUser(funUser);
                return;
            }
            funUserMapper.updateFunUser(funUser);
        } catch (Exception e) {
            log.error("存储用户失败: " + FastJsonUtil.toJSONString(funUser));
        }
    }

    public void updateUserByRecycle(FunRecycle funRecycle) {
        FunUser funUser = funUserMapper.selectFunUserByUserNo(funRecycle.getUser());
        if (funUser != null) {
            funUser.setRecentAdd(funRecycle.getAddress());
            funUser.setUpdateTime(new Date());
        }
    }

}
