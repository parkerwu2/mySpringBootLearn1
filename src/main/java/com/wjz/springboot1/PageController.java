package com.wjz.springboot1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jingzhi.wu on 2018/4/24.
 */
@Controller
@RequestMapping(value = "/head")
public class PageController {
    @RequestMapping(value = "/head")
    public String head() throws Exception{
        return "order";
    }
}
