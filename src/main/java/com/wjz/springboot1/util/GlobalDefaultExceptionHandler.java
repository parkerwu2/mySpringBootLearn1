package com.wjz.springboot1.util;

import com.wjz.springboot1.service.common.ApiBaseResponse;
import com.wjz.springboot1.service.constant.Constant;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jingzhi.wu on 2018/1/22.
 */
@ControllerAdvice
@RestController
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ApiBaseResponse defaultErrorHandler(HttpServletRequest req, Exception e)  {
        //打印异常信息：
        e.printStackTrace();
        System.out.println("client GlobalDefaultExceptionHandler.defaultErrorHandler()");
        ApiBaseResponse response = new ApiBaseResponse();
        response.setCode(Constant.COMMON_ERROR_CODE);
        response.setMessage(e.getMessage());
        return response;
    }
}
