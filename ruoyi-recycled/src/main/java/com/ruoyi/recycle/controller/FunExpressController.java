package com.ruoyi.recycle.controller;

import com.deppon.dop.module.sdk.shared.util.FastJsonUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.recycle.domain.FunRecycle;
import com.ruoyi.recycle.domain.express.TraceQueryInfo;
import com.ruoyi.recycle.domain.request.SendOrderInfoReq;
import com.ruoyi.recycle.domain.response.CommonResp;
import com.ruoyi.recycle.domain.response.TraceInfoResp;
import com.ruoyi.recycle.service.IExpressService;
import com.ruoyi.recycle.service.IFunRecycleService;
import com.ruoyi.recycle.service.IFunUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/express")
@Api("物流接口")
public class FunExpressController {

    private static final Logger log = LoggerFactory.getLogger(FunExpressController.class);

    @Autowired
    private IFunRecycleService funRecycleService;
    @Autowired
    private IExpressService expressService;
    @Autowired
    private IFunUserService userService;

    @PostMapping("/updateStatus")
    @Log
    public String updateExpressStatus(@RequestParam String timestamp, String params, String digest) {
        SendOrderInfoReq sendOrderInfo = FastJsonUtil.parseObject(params, SendOrderInfoReq.class);

        log.info("———————————————订单状态推送_请求———————————————");
        log.info(FastJsonUtil.toJSONString(sendOrderInfo));

//        String expectDigest= Base64.encodeBase64String(DigestUtils.md5Hex(params+ ExpressApiConfig.getAppKey()+timestamp).getBytes());
//        if(!expectDigest.equals(digest.trim()) || new Date().getTime()-Long.parseLong(timestamp) > 600000){
//            return "摘要或时间验证失败！";
//        }

        CommonResp resp = new CommonResp();
        resp.setResult("true");
        resp.setResultCode("1000");
        resp.setReason("成功");
        resp.setLogisticID(sendOrderInfo.getLogisticID());
        resp.setLogisticCompanyID("DEPPON");

        long id = Long.parseLong(funRecycleService.getOrderByChannel(sendOrderInfo.getLogisticID()));
        FunRecycle recycle = funRecycleService.selectFunRecycleByRecycleOrder(id);
        log.info("beforeChange: " + FastJsonUtil.toJSONString(recycle));
        if (recycle == null) {
            return "订单不存在";
        }

        expressService.judgeOrderStatus(sendOrderInfo, recycle);

        funRecycleService.updateFunRecycle(recycle);

        log.info("———————————————订单状态推送_响应———————————————");
        log.info(FastJsonUtil.toJSONString(recycle));
        return FastJsonUtil.toJSONString(resp);
    }

    @GetMapping("/queryTracing")
    @ApiOperation("收货订单物流状态查询")
    @ApiParam(name = "recycleID", type = "String")
    @Log(title = "查询轨迹")
    public AjaxResult queryTracing(@RequestParam String recycleID) {
        if (recycleID == null || recycleID.equals("")) {
            return AjaxResult.error("参数错误！", "recycleID为空");
        }
        FunRecycle funRecycle = funRecycleService.selectFunRecycleByRecycleID(Long.parseLong(recycleID));
        if (funRecycle == null) {
            return AjaxResult.error("订单不存在！", recycleID);
        }
        TraceQueryInfo queryInfo = new TraceQueryInfo(funRecycle.getExpressNum());
        TraceInfoResp traceInfoResp = FastJsonUtil.parseObject(expressService.traceQuery(queryInfo), TraceInfoResp.class);
        if (!Objects.equals(traceInfoResp.getResultCode(), "1000")) {
            return AjaxResult.error("查询物流状态失败：" + traceInfoResp.getReason(), traceInfoResp);
        }
        return AjaxResult.success(traceInfoResp.getResponseParam().getTrace_list());
    }

}
