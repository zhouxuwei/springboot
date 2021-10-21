package com.xwzhou.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "springfox")
public class Swagger3Properties {

  /**
   * 文档名称
   */
  private String title;
  /**
   * 作者名
   */
  private String name;
  /**
   * url
   */
  private String url;
  /**
   * email
   */
  private String email;
  /**
   * 版本
   */
  private String version;
  /**
   * 简介
   */
  private String description;


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
