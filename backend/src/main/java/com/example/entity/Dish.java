package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dish", autoResultMap = true)
public class Dish {
  @TableId(value = "dish_id")
  String dish_id;
  String dish_name;
  int price;
  String description;
  int likes;
  int bookings;           // 预订数
  String canteen_id;
  String category;        // 早中晚时段
  public float rating;
  int floor;              // 食堂层数
  public int rating_num;  // 评价人数
  String image;
}
