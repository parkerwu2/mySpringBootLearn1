package com.wjz.springboot1.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by jingzhi.wu on 2018/4/24.
 */
public class BusinessUtil {
    public static void processParam(BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            List<ObjectError> ls = bindingResult.getAllErrors();
            throw new Exception(ls.get(0).getDefaultMessage());
        }
    }
}
