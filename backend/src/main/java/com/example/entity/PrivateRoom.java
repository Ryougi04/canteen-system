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
@TableName(value = "private_room", autoResultMap = true)
public class PrivateRoom {
  @TableId(value = "reservation_id", type = IdType.AUTO)
  private Integer reservation_id;
  String name;
  String phone;
  Date date;
  String type;
}
