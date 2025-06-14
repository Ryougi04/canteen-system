package com.example.controller;

import com.example.entity.User;
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
  @PostMapping("/changePassword")
  @ResponseBody
  public Map<String,Object> changePassword(@RequestParam("username") String username, @RequestParam("curPassword") String curPassword, @RequestParam("newPassword") String newPassword){
    Map<String,Object> json = new HashMap<>();
    User user = userMapper.selectById(username);
    if(user.getPassword().equals(curPassword)){
      user.setPassword(newPassword);
      userMapper.updateById(user);
      json.put("code", 200);
      json.put("msg", "success");
    } else {
      user.setPassword(newPassword);
      json.put("code", 400);
      json.put("msg", "password wrong");
    }
    return json;
  }
}
