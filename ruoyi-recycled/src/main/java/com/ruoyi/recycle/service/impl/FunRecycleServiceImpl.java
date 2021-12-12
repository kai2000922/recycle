package com.ruoyi.recycle.service.impl;

import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.recycle.config.ExpressApiConfig;
import com.ruoyi.recycle.config.StatusConfig;
import com.ruoyi.recycle.domain.FunExpressPrice;
import com.ruoyi.recycle.domain.FunRecieve;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.express.PackageInfo;
import com.ruoyi.recycle.domain.express.Receiver;
import com.ruoyi.recycle.domain.express.SendOrderInfo;
import com.ruoyi.recycle.domain.express.Sender;
import com.ruoyi.recycle.domain.request.SendOrderInfoReq;
import com.ruoyi.recycle.domain.response.QueryOrderInfoResp;
import com.ruoyi.recycle.domain.response.SendOrderInfoResp;
import com.ruoyi.recycle.mapper.FunExpressPriceMapper;
import com.ruoyi.recycle.mapper.FunRecieveMapper;
import com.ruoyi.recycle.mapper.FunRecycleMapper;
import com.ruoyi.recycle.service.IExpressService;
import com.ruoyi.recycle.service.IFunRecycleService;
import com.ruoyi.recycle.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回收订单Service业务层处理
 *
 * @author ruoyi
 * @date 2021-10-07
 */
@Component("recycle")
public class FunRecycleServiceImpl implements IFunRecycleService {
    @Resource
    private FunRecycleMapper funRecycleMapper;
    @Resource
    private FunRecieveMapper funRecieveMapper;
    @Resource
    private FunExpressPriceMapper funExpressPriceMapper;
    @Resource
    private IExpressService expressService;

    private static final Logger log = LoggerFactory.getLogger(FunRecycleServiceImpl.class);

    private final String[] specialCity = new String[]{"宁波"};

    private final String remark = "此单，需压紧压实，按照实际重量开单，超30kg拆子母件邮寄，包装为免费包装。注意取件时效以及服务态度。";


    /**
     * 查询回收订单
     *
     * @param recycleID 回收订单主键
     * @return 回收订单
     */
    @Override
    public FunRecycle selectFunRecycleByRecycleID(Long recycleID) {
        return funRecycleMapper.selectFunRecycleByRecycleID(recycleID);
    }

    /**
     * 查询回收订单列表
     *
     * @param funRecycle 回收订单
     * @return 回收订单
     */
    @Override
    public List<FunRecycle> selectFunRecycleList(FunRecycle funRecycle) {
        return funRecycleMapper.selectFunRecycleList(funRecycle);
    }

    public List<FunRecycle> selectQueryFunRecycleList(FunRecycle funRecycle) {
        return funRecycleMapper.selectQueryFunRecycleList(funRecycle);
    }

    /**
     * 新增回收订单
     *
     * @param funRecycle 回收订单
     * @return 结果
     */
    @Override
    public int insertFunRecycle(FunRecycle funRecycle) {
//        funRecycle.setCreateTime(DateUtils.getNowDate());
        return funRecycleMapper.insertFunRecycle(funRecycle);
    }

    /**
     * 修改回收订单
     *
     * @param funRecycle 回收订单
     * @return 结果
     */
    @Override
    public int updateFunRecycle(FunRecycle funRecycle) {
        funRecycle.setUpdateTime(DateUtils.getNowDate());
        return funRecycleMapper.updateFunRecycle(funRecycle);
    }

    /**
     * 批量删除回收订单
     *
     * @param recycleIDs 需要删除的回收订单主键
     * @return 结果
     */
    @Override
    public int deleteFunRecycleByRecycleIDs(String recycleIDs) {
        return funRecycleMapper.deleteFunRecycleByRecycleIDs(Convert.toStrArray(recycleIDs));
    }

    /**
     * 删除回收订单信息
     *
     * @param recycleID 回收订单主键
     * @return 结果
     */
    @Override
    public int deleteFunRecycleByRecycleID(Long recycleID) {
        return funRecycleMapper.deleteFunRecycleByRecycleID(recycleID);
    }

    @Override
    public SendOrderInfo getOrder(FunRecycle funRecycle) {
        List<FunRecieve> recieveList = funRecieveMapper.selectFunRecieveByProv(funRecycle.getProv());
        FunRecieve funRecieve = null;
        for (FunRecieve recieve : recieveList) {
            if (getExpressPrice(funRecycle.getCity(), recieve.getCity()).equals("1"))
                funRecieve = recieve;
        }
        if (funRecieve == null) {
            if (funRecycle.getCity().contains("宁波")) {
                FunRecieve recieve = new FunRecieve();
                recieve.setCity("宣城市");
                funRecieve = funRecieveMapper.selectFunRecieveList(recieve).get(0);
            } else
                return null;
        }

        //设置发件人信息
        Sender sender = new Sender();
        sender.setProvince(funRecycle.getProv());
        sender.setCity(funRecycle.getCity());
        sender.setCounty(funRecycle.getArea());
        sender.setName(funRecycle.getName());
        sender.setMobile(funRecycle.getPhone());
        sender.setAddress(funRecycle.getAddress());

        //设置收件人信息
        Receiver receiver = new Receiver();
        receiver.setName(funRecieve.getName());
        receiver.setAddress(funRecieve.getAddress());
        receiver.setMobile(funRecieve.getPhone());
        receiver.setProvince(funRecieve.getProv());
        receiver.setCity(funRecieve.getCity());
        receiver.setCounty(funRecieve.getArea());
        funRecycle.setReceiveAddId(funRecieve.getReceiveID());

        //包裹信息
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setTotalNumber(1);
        packageInfo.setCargoName("衣服");
        packageInfo.setTotalWeight(funRecycle.getExpectWeight());
        if (funRecycle.getExpectWeight() >= 20)
            packageInfo.setTotalWeight(32L);
        packageInfo.setDeliveryType("1");

        //订单
        SendOrderInfo orderInfo = new SendOrderInfo();
        orderInfo.setSender(sender);
        orderInfo.setReceiver(receiver);
        orderInfo.setPackageInfo(packageInfo);
        orderInfo.setCustomerCode(ExpressApiConfig.getCount());
        orderInfo.setTransportType("PACKAGE");
        orderInfo.setGmtCommit(DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD_HH_MM_SS));
        orderInfo.setPayType("2");
        orderInfo.setOrderType("1");
        orderInfo.setCompanyCode(ExpressApiConfig.getCompanyCode());
        orderInfo.setRemark(remark);

        //渠道号码
        String channel = getChannelNum();
        if (funRecycle.getChannelNum() == null) {
            funRecycle.setChannelNum(channel);
        }
        if (orderInfo.getLogisticID() == null) {
            orderInfo.setLogisticID(channel);
        }

        //计数加1
        funRecieve.setCounter(funRecieve.getCounter() + 1);
        funRecieveMapper.updateFunRecieve(funRecieve);

        return orderInfo;
    }

    @Override
    public String getOrderByChannel(String channel) {
        return channel.split(ExpressApiConfig.getSign()).length == 2 ? channel.split(ExpressApiConfig.getSign())[1] : "";
    }

    //使用同步功能确保渠道号码的唯一性
    public synchronized String getChannelNum() {
        return CommonUtil.getUniqueNum();
    }

    public FunRecycle selectFunRecycleByRecycleOrder(Long orderNum) {
        return funRecycleMapper.selectFunRecycleByRecycleOrder(orderNum);
    }

    @Override
    public String getExpressPrice(String offsetCity, String arriveCity) {
        //截取市区后面多余的市
        offsetCity = offsetCity.split("市")[0];
        arriveCity = arriveCity.split("市")[0];

        for (String s : specialCity)
            if (s.equals(offsetCity))
                return "1";

        try {
            FunExpressPrice expressPrice = funExpressPriceMapper.selectFunExpressPriceList(new FunExpressPrice(offsetCity + arriveCity)).get(0);
            if (Double.parseDouble(expressPrice.getStandardContinue()) > 2)
                return "-1";
            else
                return "1";
        } catch (IndexOutOfBoundsException e) {
            log.error("市区不存在" + offsetCity + arriveCity);
            return "-2";
        }
    }

    public int getThisMonthOrders(String user) {
        Map<String, Object> map = new HashMap<>();
        map.put("beginTime", DateUtils.getDate());
        Date date = new Date();
        date.setMonth(date.getMonth() - 1);
        map.put("endTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, date));
        FunRecycle funRecycle = new FunRecycle();
        funRecycle.setParams(map);
        funRecycle.setUser(user);
        //获取本月内非已取消的订单
        List<FunRecycle> recycleList = funRecycleMapper.selectFunRecycleList(funRecycle);
        int counter = 0;
        for (FunRecycle recycle : recycleList) {
            if (!recycle.getOrderStatus().equals("已取消")) {
                counter++;
            }
        }
        return counter;
    }

    public void sendRequestRegular() {
        log.info("————————订单发送调度线程已启动！————————");
        List<FunRecycle> list = getWAITGETOrders();
        for (FunRecycle recycle : list) {
            if (StringUtils.isEmpty(recycle.getExpressNum()) && recycle.getExpectTime().getTime() - new Date().getTime() <= 1000 * 60 * 30) {
                SendOrderInfo orderInfo = getOrder(recycle);
                SendOrderInfoResp sendResp = FastJsonUtil.parseObject(expressService.sendOrder(orderInfo), SendOrderInfoResp.class);
                if (sendResp.getResult().equals("false")) {
                    recycle.setOrderStatus(StatusConfig.order_be_cancel);
                    recycle.setCacelReason(sendResp.getReason());
                    log.error("预约订单创建失败: " + FastJsonUtil.toJSONString(sendResp));
                }
                recycle.setExpressNum(sendResp.getMailNo());
                recycle.setCacelReason("订单已创建");
                recycle.setChannelNum(sendResp.getLogisticID());
                recycle.setOrderNum(getOrderByChannel(sendResp.getLogisticID()));
                funRecycleMapper.updateFunRecycle(recycle);
            } else {
                log.info("———————————无需发送订单—————————");
            }
        }
    }

    public void queryOrderStatus() {
        log.info("————————订单状态查询线程已启动！————————");
        FunRecycle temp = new FunRecycle();
        temp.setStatu("0");
        List<FunRecycle> list = selectQueryFunRecycleList(temp);
        for (FunRecycle recycle : list) {
//            if (recycle.getActualWeight() == null || recycle.getActualWeight() == 0 )
//                continue;
            QueryOrderInfoResp resp = FastJsonUtil.parseObject(expressService.queryOrder(recycle.getChannelNum()), QueryOrderInfoResp.class);
            if (resp.getResultCode() == null || !resp.getResultCode().equals("1000")) {
                log.info("订单不存在！");
                continue;
            }
            SendOrderInfoReq orderInfo = resp.getResponseParam();
            log.info("resp: " + FastJsonUtil.toJSONString(orderInfo));

            //响应数据处理
            orderInfo.setStatusType(exchangeStatusToEng(orderInfo.getStatusType()));
            expressService.judgeOrderStatus(orderInfo, recycle);
            funRecycleMapper.updateFunRecycle(recycle);
        }
        log.info("————————订单状态查询线程结束！————————");
    }

    public List<FunRecycle> getWAITGETOrders() {
        FunRecycle temp = new FunRecycle();
        temp.setOrderStatus(StatusConfig.order_wait_get);
        return funRecycleMapper.selectFunRecycleList(temp);
    }

    public String exchangeStatusToEng(String status) {
        switch (status) {
            case "已开单":
                return "GOT";
            case "已受理":
                return "ACCEPT";
            case "正常签收":
                return "SIGNSUCCESS";
            case "异常签收":
                return "SIGNFAILED";
            case "接货中":
                return "RECEIPTING";
            case "已撤销":
                return "CANCEL";
            case "已退回":
                return "GOBACK";
            case "揽货失败":
                return "FAILGOT";
            case "已作废":
                return "INVALID";
            default:
                return "";
        }
    }

}
