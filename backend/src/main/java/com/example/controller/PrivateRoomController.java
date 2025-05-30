package com.example.controller;

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
  @PostMapping("/add")
  @ResponseBody
  public Map<String,Object> add(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date date, @RequestParam("roomType") String type){
    Map<String,Object> json = new HashMap<>();
    PrivateRoom privateRoom = new PrivateRoom();
    privateRoom.setName(name);
    privateRoom.setPhone(phone);
    privateRoom.setDate(date);
    privateRoom.setType(type);
    System.out.println(privateRoom);

    privateRoomMapper.insert(privateRoom);
    json.put("code", 200);
    json.put("msg", "success");
    return json;
  }
}
