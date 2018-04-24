package com.wjz.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.wjz.springboot1.persistence.dao")
//@EnableDiscoveryClient
public class SpringBootLearn1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearn1Application.class, args);
	}
}
