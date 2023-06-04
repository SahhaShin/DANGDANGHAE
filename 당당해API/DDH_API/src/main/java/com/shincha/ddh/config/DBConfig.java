package com.shincha.ddh.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.shincha.ddh.model.dao")
public class DBConfig {

}
