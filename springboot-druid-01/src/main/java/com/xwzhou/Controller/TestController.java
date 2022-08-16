package com.xwzhou.Controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping(value = "/select111")
  public List<Map<String, Object>> select() {
    String sql = "select * from test";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    return maps;
  }


  @GetMapping(value = "/selectOne2222")
  public List<Map<String, Object>> selectOne() {
    String sql = "select * from test where id= '10'";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    return maps;
  }
}
