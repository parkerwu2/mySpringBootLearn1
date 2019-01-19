package com.wjz.springboot1.eshbase;

/**
 * Created by parker on 2019/1/5.
 */

import lombok.Data;

@Data
public class Doc {
    private Integer id;
    private String title;

    private String describe;

    private String content;

    private String author;
}
