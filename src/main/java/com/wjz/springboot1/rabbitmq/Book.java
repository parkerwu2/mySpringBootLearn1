package com.wjz.springboot1.rabbitmq;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by parker on 2019/1/20.
 */
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = -2164058270260403154L;

    private String id;
    private String name;
}
