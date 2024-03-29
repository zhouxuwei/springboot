package com.xwzhou.Controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试")
@RestController
public class JdbcController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Operation(summary = "测试接口")
  @GetMapping(value = "/select")
  public List<Map<String, Object>> select() {
    String sql = "select * from test";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    return maps;
  }


  @GetMapping(value = "/selectOne")
  public List<Map<String, Object>> selectOne() {
    String sql = "select * from test where id= '10'";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    return maps;
  }
}
