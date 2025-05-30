package com.example.controller;

import com.example.mapper.CanteenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/canteen")
@CrossOrigin
public class CanteenController {
    @Autowired
    private CanteenMapper canteenMapper;
    @GetMapping("/getAll")
    @ResponseBody
    public Map<String,Object> getAll(){
        Map<String,Object> json = new HashMap<>();
        json.put("code", 200);
        json.put("post", canteenMapper.selectList(null));
        return json;
    }
}
