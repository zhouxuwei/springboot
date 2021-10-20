package com.xwzhou.controller;

import com.xwzhou.dao.TestDao;
import com.xwzhou.po.Test;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="测试业务",value = "value")
@RestController
public class JdbcController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TestDao testDao;

  @Operation(summary = "查询所有数据")
  @GetMapping(value = "/select")
  public List<Test> select() {
    return testDao.selectAll();
  }


  @GetMapping(value = "/selectOne")
  public List<Map<String, Object>> selectOne() {
    String sql = "select * from test where id= '10'";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    return maps;
  }
}
