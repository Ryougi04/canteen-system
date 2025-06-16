package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "users", autoResultMap = true)
public class User {
  @TableId(value = "username")
  String username;
  String password;
  String sid;
  String college;
  String phone;
  String avatar;
  boolean admin;
}
