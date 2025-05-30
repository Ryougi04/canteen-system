package com.example.controller;

import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
  @Autowired
  private UserMapper userMapper;
  @GetMapping("/get")
  @ResponseBody
  public Map<String,Object> getUser(@RequestParam("username") String username){
    Map<String,Object> json = new HashMap<>();
    json.put("code", 200);
    json.put("user", userMapper.selectById(username));
    return json;
  }
}
