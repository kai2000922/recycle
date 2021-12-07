package com.ruoyi.recycle.utils;

import com.ruoyi.recycle.config.ExpressApiConfig;

import java.util.Date;

public class CommonUtil {

    public synchronized static String getUniqueNum() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ExpressApiConfig.getSign() + new Date().getTime();
    }

    public synchronized static String getUniqueNumer() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "" + new Date().getTime();
    }

}
