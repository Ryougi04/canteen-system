package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.util.IntegerListTypeHandler;
import com.example.util.StringListTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dish", autoResultMap = true)
public class Dish {
  @TableId(value = "dish_id", type = IdType.AUTO)
  private Integer dish_id;
  String dish_name;
  float price;
  String description;
  int likes;
  int bookings;    // 预订数
  @TableField(typeHandler = StringListTypeHandler.class)
  List<String> canteen_id;
  @TableField(typeHandler = IntegerListTypeHandler.class)
  List<Integer> floor;    // 食堂层数
  @TableField(typeHandler = StringListTypeHandler.class)
  List<String> category;  // 早中晚时段
  public float rating;
  public int rating_num;  // 评价人数
  String image;
}
