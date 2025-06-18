package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Dish;
import com.example.entity.DishBooking;
import com.example.mapper.DishBookingMapper;
import com.example.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

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
    @PostMapping("/updateRating")
    @ResponseBody
    public Map<String,Object> updateRating(@RequestParam("dish_id") Integer dish_id, @RequestParam("rating") float rating, @RequestParam("flag") boolean flag, @RequestParam("last_rating") float last_rating){
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
    public Map<String,Object> modify(@RequestParam("dish_id") Integer dish_id, @RequestParam("dish_name") String dish_name, @RequestParam("description") String description){
        Map<String,Object> json = new HashMap<>();
        Dish dish = dishMapper.selectById(dish_id);
        dish.setDish_name(dish_name);
        dish.setDescription(description.replace("描述", "\n描述"));
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
    @PutMapping("/updateMenu")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public Map<String,Object> updatePrice(@RequestBody List<Map<String,Object>> updateMenu){
        Map<String,Object> json = new HashMap<>();
        updateMenu.forEach(map -> {
            Dish dish = dishMapper.selectById((Serializable) map.get("dish_id"));
            Number price = (Number) map.get("price");
            dish.setPrice(price.floatValue());
            dish.setCategory((List<String>) map.get("category"));
            dish.setFloor((List<Integer>) map.get("floor"));
            dish.setCanteen_id((List<String>) map.get("canteen_id"));
            dish.setAvailable(map.get("available").toString());
            dishMapper.updateById(dish);
        });
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @PostMapping("/updateBooking")
    @ResponseBody
    public Map<String,Object> updateBooking(@RequestParam("dish_id") Integer dish_id, @RequestParam("change") Integer change){
        Map<String,Object> json = new HashMap<>();
        Dish dish = dishMapper.selectById(dish_id);
        dish.setBookings(dish.getBookings() + change);
        dishMapper.updateById(dish);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @Autowired
    private DishBookingMapper dishBookingMapper;
    @GetMapping("/getBooking")
    @ResponseBody
    public Map<String,Object> getBooking(@RequestParam("username") String username){
        Map<String,Object> json = new HashMap<>();
        QueryWrapper<DishBooking> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        json.put("code", 200);
        json.put("booking", dishBookingMapper.selectList(queryWrapper));
        return json;
    }
    @PostMapping("/addBooking")
    @ResponseBody
    public Map<String,Object> addBooking(@RequestParam("username") String username, @RequestParam("dish_id") int dish_id, @RequestParam("canteen_id") String canteen_id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("category") String category){
        Map<String,Object> json = new HashMap<>();
        DishBooking dishBooking = new DishBooking();
        dishBooking.setUsername(username);
        dishBooking.setDish_id(dish_id);
        dishBooking.setCanteen_id(canteen_id);
        dishBooking.setDate(date);
        dishBooking.setCategory(category);
        dishBookingMapper.insert(dishBooking);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @PostMapping("/deleteBooking")
    @ResponseBody
    public Map<String,Object> deleteBooking(@RequestParam("username") String username, @RequestParam("dish_id") int dish_id, @RequestParam("canteen_id") String canteen_id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        Map<String,Object> json = new HashMap<>();
        QueryWrapper<DishBooking> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("dish_id", dish_id);
        queryWrapper.eq("canteen_id", canteen_id);
        queryWrapper.eq("date", date);
        dishBookingMapper.delete(queryWrapper);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
}
