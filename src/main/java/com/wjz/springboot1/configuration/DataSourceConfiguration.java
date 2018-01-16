package com.wjz.springboot1.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
@Configuration
@PropertySource("classpath:application.properties")
@Data
public class DataSourceConfiguration {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver}")
    private String driver;

//    @Bean
//    public BasicDataSource dataSource(){
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(driver);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxIdle(maxIdel);
//        dataSource.setMaxWait(maxWait);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(true);
//        return dataSource;
//    }
}
