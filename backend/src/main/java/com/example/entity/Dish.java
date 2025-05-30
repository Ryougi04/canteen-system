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
  @TableId(value = "dish_id", type = IdType.ASSIGN_ID)
  String dish_id;
  String dish_name;
  Integer price;
  String description;
  Integer likes;
  Integer bookings;
  String canteen_id;
  String category;    // 早/中/晚时段
  Float rating;
  Integer floor;
}
