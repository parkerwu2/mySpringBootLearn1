package com.wjz.springboot1.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by jingzhi.wu on 2018/8/14.
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
}
