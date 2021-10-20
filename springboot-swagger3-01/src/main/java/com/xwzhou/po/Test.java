package com.xwzhou.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
@ApiModelProperty("主键")
  private Integer id;
  @ApiModelProperty("名称")
  private String name;
  @ApiModelProperty("性别")
  private String sex;
  @ApiModelProperty("年龄")
  private Integer age;

}
