package com.xwzhou.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @author xwzhou swagger3 配置
 */
//表示在注入Swagger3In情况下才会执行当前自动配置
@ConditionalOnBean(Swagger3In.class)
//使Swagger3Properties.class中的@ConfigurationProperties注解生效
@EnableConfigurationProperties(Swagger3Properties.class)
//注解配合@Bean注解来生成Bean：这与JavaConfig方式无异，目的是生成Bean并放入容器。
@Configuration
//增强文档knife4j
@EnableKnife4j
@EnableOpenApi
public class Swagger3Config {

  @Value("${springfox.package:com.xwzhou.controller}")
  private String path;
  @Autowired
  private Swagger3Properties properties;

  /**
   * swagger3的配置文件
   */
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage(path))
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
        .paths(PathSelectors.any())
        .build();
  }

  /**
   * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(properties.getTitle())
        .contact(new Contact(properties.getName(), properties.getUrl(), properties.getEmail()))
        .version(properties.getVersion())
        .description(properties.getDescription())
        .build();
  }


}
