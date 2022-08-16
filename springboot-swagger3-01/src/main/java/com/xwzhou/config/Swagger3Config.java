package com.xwzhou.config;

import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * swagger3 配置
 */
//@EnableKnife4j
@Configuration
//@EnableOpenApi
public class Swagger3Config {

  /**
   * swagger3的配置文件
   */
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
        .paths(PathSelectors.any())
        .build()
        .globalRequestParameters(getGlobalRequestParameters())
        .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
        .globalResponses(HttpMethod.POST, getGlobalResponseMessage())
        .globalResponses(HttpMethod.DELETE, getGlobalResponseMessage())
        .globalResponses(HttpMethod.PUT, getGlobalResponseMessage());
  }

  /**
   * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
   */
  private ApiInfo apiInfo() {
    // 获取工程名称
    String projectName = System.getProperty("user.dir");
    return new ApiInfoBuilder()
        .title(projectName.substring(projectName.lastIndexOf("\\") + 1) + " API接口文档")
        .contact(new Contact("xwzhou", "", "xxxxxx@qq.com"))
        .version("1.0")
        .description("API文档")
        .build();
  }

  /**
   * 生成全局通用参数 可添加多个
   *
   * @return
   */

  private List<RequestParameter> getGlobalRequestParameters() {
    List<RequestParameter> parameters = new ArrayList<>();
    parameters.add(new RequestParameterBuilder()
        .name("token")
        .description("令牌")
        .required(false)
        .in(ParameterType.HEADER)
        .build());
    return parameters;
  }

  /**
   * 生成通用响应信息
   *
   * @return
   */
  private List<Response> getGlobalResponseMessage() {
    List<Response> responseList = new ArrayList<>();
    responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
    return responseList;
  }

}
