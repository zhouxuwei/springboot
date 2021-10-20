package com.xwzhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.xwzhou.dao")
@SpringBootApplication
public class SpringbootMybatis01Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootMybatis01Application.class, args);
  }

}
