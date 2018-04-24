package com.wjz.springboot1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
public class RestUtil {
    public static String objectToJson(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = mapper.writeValueAsString(o);
        return mapJakcson;
    }
}
