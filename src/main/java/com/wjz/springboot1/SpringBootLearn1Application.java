package com.wjz.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
//@EnableScheduling
@MapperScan("com.wjz.springboot1.persistence.dao")
@EnableCaching
public class SpringBootLearn1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearn1Application.class, args);
	}

//	@Bean
//	public TaskScheduler taskScheduler(){
//		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//		taskScheduler.setPoolSize(16);
//		taskScheduler.setThreadNamePrefix("springboot-task");
//		return taskScheduler;
//	}

}
