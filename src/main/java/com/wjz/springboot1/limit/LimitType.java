package com.wjz.springboot1.limit;

/**
 * Created by jingzhi.wu on 2019/1/22.
 */
public enum LimitType {
    /**
     * 自定义key
     */
    CUSTOMER,
    /**
     * 根据请求者IP
     */
    IP;
}
