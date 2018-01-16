package com.wjz.springboot1.service.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
@Data
public class ApiBaseResponse implements Serializable {
    private String code = "00";
    private String message;
}
