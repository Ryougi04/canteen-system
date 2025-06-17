package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dish_booking", autoResultMap = true)
public class DishBooking {
  @TableId(value = "booking_id", type = IdType.AUTO)
  private Integer booking_id;
  String username;
  Date date;
  int dish_id;
  String canteen_id;
  String category;
}
