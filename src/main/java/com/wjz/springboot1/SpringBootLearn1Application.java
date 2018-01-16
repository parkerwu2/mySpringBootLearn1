package com.wjz.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wjz.springboot1.persistence.dao")
public class SpringBootLearn1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearn1Application.class, args);
	}
}
