package com.wjz.springboot1.es;

import com.wjz.springboot1.util.RestUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jingzhi.wu on 2019/1/23.
 */
@RestController
@RequestMapping(value = "/es2")
public class EsController {
    @Autowired
    private CityESServiceImpl cityESService;

    @RequestMapping("/query")
    public String query(@RequestBody String q) {
        List<City> cities = cityESService.query2(q);
        if (CollectionUtils.isNotEmpty(cities)){
            return RestUtil.objectToJSONString(cities);
        }else {
            return null;
        }
    }
}
