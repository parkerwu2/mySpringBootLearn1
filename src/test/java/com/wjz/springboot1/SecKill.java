package com.wjz.springboot1;

import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jingzhi.wu on 2019/3/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecKill {
    @Autowired
    private org.springframework.web.context.WebApplicationContext context;
    MockMvc mockMvc;

    @Before
    public void before() {
        //可以对所有的controller来进行测试
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    private static RestTemplate rest = new RestTemplate();

    private AtomicInteger id = new AtomicInteger(0);
    @Test
    public void seckill2(){
        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        seckill("parker"+ id.getAndIncrement());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

//    @Test
    public void seckill(String name) throws Exception {
        String uri = "/seckill2/buy";
//        String param = "maike";
        Map<String, String> map = new HashMap<>();
        map.put("userName", name);
        String p = JSONObject.toJSONString(map);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).
                contentType(MediaType.APPLICATION_JSON).content(p))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }
}
