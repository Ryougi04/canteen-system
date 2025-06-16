package com.example.controller;

import com.example.entity.Overview;
import com.example.mapper.OverviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/overview")
@CrossOrigin
public class OverviewController {
  @Autowired
  private OverviewMapper overviewMapper;
  @GetMapping("/getAll")
  @ResponseBody
  public Map<String,Object> getAll(){
    Map<String,Object> json = new HashMap<>();
    json.put("code", 200);
    json.put("overviewData", overviewMapper.selectList(null));
    return json;
  }
  @PostMapping("/update")
  @ResponseBody
  public Map<String,Object> update(@RequestParam("item") String item, @RequestParam("value") int value){
    Map<String,Object> json = new HashMap<>();
    Overview overview = overviewMapper.selectById(item);
    overview.num += value;
    overviewMapper.updateById(overview);
    json.put("code", 200);
    json.put("msg", "success");
    return json;
  }
}
