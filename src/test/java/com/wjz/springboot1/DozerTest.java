package com.wjz.springboot1;

import com.wjz.springboot1.dozer.EJBGenerator;
import com.wjz.springboot1.service.dto.DozerRequest;
import com.wjz.springboot1.service.dto.DozerTarget;
import com.wjz.springboot1.service.dto.QueryOrderByNoReqDto;
import com.wjz.springboot1.service.dto.UpdateTimeReqDto;
import com.wjz.springboot1.util.RestUtil;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jingzhi.wu on 2019/1/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = {"classpath:dozer/dozer-mapping.xml"})
public class DozerTest {
    @Autowired
    private final static EJBGenerator ejbGenerator = new EJBGenerator();

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Test
    public void test2(){
        DozerRequest dozerRequest = new DozerRequest();
        DozerTarget dozerTarget = new DozerTarget();
        QueryOrderByNoReqDto queryOrderByNoReqDto =new QueryOrderByNoReqDto();
        queryOrderByNoReqDto.setOrderNo("abc123");
        UpdateTimeReqDto updateTimeReqDto = new UpdateTimeReqDto();
        updateTimeReqDto.setOrderNo("xx22");
        updateTimeReqDto.setRefundDate(new Date());
        List<QueryOrderByNoReqDto> list =new ArrayList<>();
        list.add(queryOrderByNoReqDto);
        dozerRequest.setId(1);
        dozerRequest.setUpdateTimeReqDto(updateTimeReqDto);
        dozerRequest.setQueryOrderByNoReqDtos(list);
        dozerTarget= dozerBeanMapper.map(dozerRequest,DozerTarget.class);
        String result = RestUtil.objectToJSONString(dozerTarget);
        System.out.println("result is:" + result);
    }

    @Test
    public void testDozer(){
        DozerRequest dozerRequest = new DozerRequest();
        DozerTarget dozerTarget = new DozerTarget();
        QueryOrderByNoReqDto queryOrderByNoReqDto =new QueryOrderByNoReqDto();
        queryOrderByNoReqDto.setOrderNo("abc123");
        UpdateTimeReqDto updateTimeReqDto = new UpdateTimeReqDto();
        updateTimeReqDto.setOrderNo("xx22");
        updateTimeReqDto.setRefundDate(new Date());
        List<QueryOrderByNoReqDto> list =new ArrayList<>();
        list.add(queryOrderByNoReqDto);
        dozerRequest.setId(1);
        dozerRequest.setUpdateTimeReqDto(updateTimeReqDto);
        dozerRequest.setQueryOrderByNoReqDtos(list);

        //执行复制
        dozerTarget = ejbGenerator.convert(dozerRequest, DozerTarget.class);
        String result = RestUtil.objectToJSONString(dozerTarget);
        System.out.println(result);
    }

}
