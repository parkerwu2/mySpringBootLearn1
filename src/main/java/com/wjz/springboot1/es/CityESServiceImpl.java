package com.wjz.springboot1.es;

import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityESServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    CityRepository cityRepository;

    @Override
    public String saveCity(City city) {

        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> searchCity(Integer pageNumber,
                                 Integer pageSize,
                                 String searchContent) {
//        // 分页参数
//        Pageable pageable = new PageRequest(pageNumber, pageSize);
//
//        // Function Score Query
////        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
////                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("cityname", searchContent)),
////                        ScoreFunctionBuilders.weightFactorFunction(1000))
////                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
////                        ScoreFunctionBuilders.weightFactorFunction(100));
//
//        FieldValueFactorFunctionBuilder cityname = ScoreFunctionBuilders.fieldValueFactorFunction("cityname").
//                modifier(FieldValueFactorFunction.Modifier.LN1P).factor(1000);
//        FieldValueFactorFunctionBuilder description = ScoreFunctionBuilders.fieldValueFactorFunction("description").
//                modifier(FieldValueFactorFunction.Modifier.LN1P).factor(100);
//        FunctionScoreQueryBuilder.FilterFunctionBuilder[] functionScoreQueryBuilder =
//                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
//                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(cityname),
//                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(description)};
//        // 创建搜索 DSL 查询
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withPageable(pageable)
//                .withQuery(functionScoreQueryBuilder).build();
//
//        LOGGER.info("\n searchCity(): searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());
//
//        Page<City> searchPageResults = cityRepository.search(searchQuery);
//        return searchPageResults.getContent();
        return null;
    }

    public List<City> query2(String q){
        List<City> cities = new ArrayList<>();
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(q);
//        Iterable<City> list = this.cityRepository.findAll(Sort.by("cityname").ascending());
//        Iterable<City> list = this.cityRepository.search(builder);
        Iterable<City> list = this.cityRepository.findAll();
        Iterator<City> iterator = list.iterator();
        while(iterator.hasNext()){
            cities.add(iterator.next());
        }
        return cities;
    }

}
