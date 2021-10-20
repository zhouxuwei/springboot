package com.xwzhou.dao;

import com.xwzhou.po.Test;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

  List<Test> selectAll();
}
