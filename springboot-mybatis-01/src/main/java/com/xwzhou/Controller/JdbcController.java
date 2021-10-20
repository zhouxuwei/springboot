package com.xwzhou.Controller;

import com.xwzhou.dao.TestDao;
import com.xwzhou.po.Test;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TestDao testDao;

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
