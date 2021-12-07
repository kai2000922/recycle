package com.ruoyi.recycle.domain.express;

public class QueryOrderInfo {
    private String logisticID;
    private final String logisticCompanyID = "DEPPON";

    public QueryOrderInfo() {
    }

    public QueryOrderInfo(String logisticID) {
        this.logisticID = logisticID;
    }

    public void setLogisticID(String logisticID) {
        this.logisticID = logisticID;
    }

    public String getLogisticID() {
        return logisticID;
    }

    public String getLogisticCompanyID() {
        return logisticCompanyID;
    }
}
