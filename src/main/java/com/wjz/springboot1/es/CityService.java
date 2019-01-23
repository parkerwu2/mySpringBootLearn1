package com.wjz.springboot1.es;

import java.util.List;

/**
 * Created by jingzhi.wu on 2019/1/23.
 */
public interface CityService {
    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    String saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);

    List<City> query2(String q);
}
