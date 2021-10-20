package com.xwzhou.controller;

import com.xwzhou.dto.UserDto;
import java.util.Arrays;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping(value = "/aboutUs")
  public String sayHello(Model model){
    model.addAttribute("name","<h1>哈哈哈哈！</h1>");
    model.addAttribute("userList", Arrays.asList(new UserDto(UUID.randomUUID().toString(),"张三","男")
        ,new UserDto(UUID.randomUUID().toString(),"李四","女")));

    return "about-us";
  }
}
