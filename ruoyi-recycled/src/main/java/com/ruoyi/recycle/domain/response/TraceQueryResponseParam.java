/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.response;

import java.util.List;

/**
 * Auto-generated: 2021-11-03 1:10:8
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TraceQueryResponseParam {

    private List<Trace_list> trace_list;
    private String tracking_number;

    public void setTrace_list(List<Trace_list> trace_list) {
        this.trace_list = trace_list;
    }

    public List<Trace_list> getTrace_list() {
        return trace_list;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getTracking_number() {
        return tracking_number;
    }

}