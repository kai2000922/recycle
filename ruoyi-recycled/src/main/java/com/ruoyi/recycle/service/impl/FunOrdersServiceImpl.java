package com.ruoyi.recycle.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.job.TaskException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.recycle.domain.FunOrders;
import com.ruoyi.recycle.mapper.FunOrdersMapper;
import com.ruoyi.recycle.service.IFunOrdersService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商城订单Service业务层处理
 *
 * @author ruoyi
 * @date 2021-11-03
 */
@Component("orderTask")
public class FunOrdersServiceImpl implements IFunOrdersService {
    @Resource
    private FunOrdersMapper funOrdersMapper;
    @Autowired
    private ISysJobService jobService;

    private static final Logger log = LoggerFactory.getLogger(FunOrdersServiceImpl.class);

    /**
     * 查询商城订单
     *
     * @param ordersID 商城订单主键
     * @return 商城订单
     */
    @Override
    public FunOrders selectFunOrdersByOrdersID(Long ordersID) {
        return funOrdersMapper.selectFunOrdersByOrdersID(ordersID);
    }

    /**
     * 查询商城订单列表
     *
     * @param funOrders 商城订单
     * @return 商城订单
     */
    @Override
    public List<FunOrders> selectFunOrdersList(FunOrders funOrders) {
        return funOrdersMapper.selectFunOrdersList(funOrders);
    }

    /**
     * 新增商城订单
     *
     * @param funOrders 商城订单
     * @return 结果
     */
    @Override
    public int insertFunOrders(FunOrders funOrders) {
        funOrders.setCreateTime(DateUtils.getNowDate());
        return funOrdersMapper.insertFunOrders(funOrders);
    }

    /**
     * 修改商城订单
     *
     * @param funOrders 商城订单
     * @return 结果
     */
    @Override
    public int updateFunOrders(FunOrders funOrders) {
        funOrders.setUpdateTime(DateUtils.getNowDate());
        return funOrdersMapper.updateFunOrders(funOrders);
    }

    /**
     * 批量删除商城订单
     *
     * @param ordersIDs 需要删除的商城订单主键
     * @return 结果
     */
    @Override
    public int deleteFunOrdersByOrdersIDs(String ordersIDs) {
        String[] ids = Convert.toStrArray(ordersIDs);
        for (String id : ids) {
            FunOrders orders = funOrdersMapper.selectFunOrdersByOrdersID(Long.parseLong(id));
            orders.setStatu("1");
            funOrdersMapper.updateFunOrders(orders);
        }
        return ids.length;
    }

    /**
     * 删除商城订单信息
     *
     * @param ordersID 商城订单主键
     * @return 结果
     */
    @Override
    public int deleteFunOrdersByOrdersID(Long ordersID) {
        return funOrdersMapper.deleteFunOrdersByOrdersID(ordersID);
    }

    @Override
    public String importOrders(List<FunOrders> ordersList, Boolean isUpdateSupport) {
        if (StringUtils.isNull(ordersList) || ordersList.size() == 0) {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (FunOrders orders : ordersList) {
            try {
                // 验证是否存在这个用户
                boolean userFlag = funOrdersMapper.selectFunOrdersByOrdersID(orders.getOrdersID()) != null;
                if (!userFlag) {
                    funOrdersMapper.insertFunOrders(orders);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、订单" + orders.getOrdersID() + " 导入成功");
                } else if (isUpdateSupport) {
                    successNum++;
                    FunOrders funOrders = funOrdersMapper.selectFunOrdersByOrdersID(orders.getOrdersID());
                    funOrders.setOrdersStatus("已发货");
                    funOrders.setExpressNum(orders.getExpressNum());
                    funOrders.setUpdateTime(new Date());
                    funOrdersMapper.updateFunOrders(funOrders);

                    sendUpdateDoneTask(Math.toIntExact(funOrders.getOrdersID()));
                    successMsg.append("<br/>" + successNum + "、订单" + orders.getOrdersID() + "已更新");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、订单 " + orders.getOrdersID() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、订单 " + orders.getOrdersID() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    public int updateOrdersDone(Integer ordersID) {
        FunOrders funOrders = funOrdersMapper.selectFunOrdersByOrdersID((long) ordersID);
        if (funOrders != null) {
            funOrders.setOrdersStatus("已完成");
            return funOrdersMapper.updateFunOrders(funOrders);
        } else {
            log.error("ID不存在" + ordersID);
            return -1;
        }
    }

    public void sendUpdateDoneTask(int ordersID) {
        SysJob job = new SysJob();
        job.setJobName(ordersID + "订单定时完成任务");
        job.setJobGroup("DEFAULT");
        job.setInvokeTarget("orderTask.updateOrdersDone(" + ordersID + ")");
        String corn = "0 " + new Date().getMinutes() + " " + new Date().getHours() + " " + (new Date().getDay() + 5) + " " + (new Date().getMonth() + 1) + " ?";
        job.setCronExpression(corn);
        job.setMisfirePolicy("1");
        job.setConcurrent("1");
        job.setStatus("0");
        try {
            jobService.insertJob(job);
        } catch (SchedulerException | TaskException e) {
            e.printStackTrace();
        }
    }
}
