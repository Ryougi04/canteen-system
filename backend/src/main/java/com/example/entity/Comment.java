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
@TableName(value = "comment", autoResultMap = true)
public class Comment {
  @TableId(value = "comment_id")
  String comment_id;
  String content;
  String dish_id;
  String username;
}