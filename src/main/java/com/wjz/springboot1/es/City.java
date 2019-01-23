package com.wjz.springboot1.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by jingzhi.wu on 2019/1/23.
 */
@Data
@Document(indexName = "api", type = "city")
public class City implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * 城市编号
     */
    private String id;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 城市评分
     */
    private String score;

}
