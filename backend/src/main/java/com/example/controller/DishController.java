package com.example.controller;

import com.example.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dish")
@CrossOrigin
public class DishController {
    @Autowired
    private DishMapper dishMapper;
    @GetMapping("/getAll")
    @ResponseBody
    public Map<String,Object> getAll(){
        Map<String,Object> json = new HashMap<>();
        json.put("code", 200);
        json.put("dish", dishMapper.selectList(null));
//        System.out.println(json);
        return json;
    }
}
