package com.example.controller;

import com.example.entity.Dish;
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
        return json;
    }
    @GetMapping("/getById")
    @ResponseBody
    public Map<String,Object> getById(@RequestParam("dish_id") Integer dish_id){
        Map<String,Object> json = new HashMap<>();
        json.put("code", 200);
        json.put("dish", dishMapper.selectById(dish_id));
        return json;
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestParam("dish_id") Integer dish_id, @RequestParam("rating") float rating, @RequestParam("flag") boolean flag, @RequestParam("last_rating") float last_rating){
        Map<String,Object> json = new HashMap<>();
        Dish dish = dishMapper.selectById(dish_id);
        if(flag) {
            dish.rating_num++;
        } else {
            if (dish.rating_num == 1) {
                dish.rating = 0;
            } else {
                dish.rating -= (last_rating - dish.rating) / (dish.rating_num - 1);
            }
        }
        dish.rating += (rating - dish.rating) / dish.rating_num;
        dishMapper.updateById(dish);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> add(@RequestParam("dish_name") String dish_name, @RequestParam("description") String description){
        Map<String,Object> json = new HashMap<>();
        Dish dish = new Dish();
        dish.setDish_name(dish_name);
        dish.setDescription(description);
        dishMapper.insert(dish);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @PostMapping("/modify")
    @ResponseBody
    public Map<String,Object> update(@RequestParam("dish_id") Integer dish_id, @RequestParam("dish_name") String dish_name, @RequestParam("description") String description){
        Map<String,Object> json = new HashMap<>();
        Dish dish = dishMapper.selectById(dish_id);
        dish.setDish_name(dish_name);
        dish.setDescription(description);
        dishMapper.updateById(dish);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("dish_id") Integer dish_id){
        Map<String,Object> json = new HashMap<>();
        dishMapper.deleteById(dish_id);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
}
