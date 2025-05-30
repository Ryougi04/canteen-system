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
@TableName(value = "cantten", autoResultMap = true)
public class Canteen {
  @TableId(value = "canteen_id")
  String canteen_id;
  String canteen_name;
  String position;
  String open_time;
}
