package com.xwzhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xwzhou.dao")
@SpringBootApplication
public class SpringbootSwagger301Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootSwagger301Application.class, args);
  }

}
