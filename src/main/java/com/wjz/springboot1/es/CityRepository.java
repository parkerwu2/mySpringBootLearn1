package com.wjz.springboot1.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by jingzhi.wu on 2019/1/23.
 */
public interface CityRepository extends ElasticsearchRepository<City, Long> {
}
