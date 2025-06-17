package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.PrivateRoom;
import com.example.mapper.PrivateRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/private-room")
@CrossOrigin
public class PrivateRoomController {
  @Autowired
  private PrivateRoomMapper privateRoomMapper;
  @GetMapping("/getAll")
  @ResponseBody
  public Map<String,Object> getAll(){
    Map<String,Object> json = new HashMap<>();
    json.put("code", 200);
    json.put("reservation", privateRoomMapper.selectList(null));
    return json;
  }
  @PostMapping("/add")
  @ResponseBody
  public Map<String,Object> add(@RequestParam("username") String username, @RequestParam("phone") String phone, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date, @RequestParam("type") String type){
    Map<String,Object> json = new HashMap<>();
    PrivateRoom privateRoom = new PrivateRoom();
    privateRoom.setUsername(username);
    privateRoom.setPhone(phone);
    privateRoom.setDate(date);
    privateRoom.setType(type);
    privateRoomMapper.insert(privateRoom);
    json.put("code", 200);
    json.put("msg", "success");
    return json;
  }
  @GetMapping("/getByUser")
  @ResponseBody
  public Map<String,Object> getByUser(@RequestParam("username") String username){
    Map<String,Object> json = new HashMap<>();
    QueryWrapper<PrivateRoom> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    json.put("code", 200);
    json.put("reservation", privateRoomMapper.selectList(queryWrapper));
    return json;
  }
}
