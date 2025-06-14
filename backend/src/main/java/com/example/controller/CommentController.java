package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @GetMapping("/getById")
    @ResponseBody
    public Map<String,Object> getById(@RequestParam("dish_id") String dish_id){
        Map<String,Object> json = new HashMap<>();
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dish_id", dish_id);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        json.put("code", 200);
        json.put("comment", comments);
        return json;
    }
    @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> add(@RequestParam("content") String content, @RequestParam("dish_id") String dish_id, @RequestParam("username") String username){
        Map<String,Object> json = new HashMap<>();
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setDish_id(dish_id);
        comment.setUsername(username);
        commentMapper.insert(comment);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("comment_id") Integer comment_id){
        Map<String,Object> json = new HashMap<>();
        commentMapper.deleteById(comment_id);
        json.put("code", 200);
        json.put("msg", "success");
        return json;
    }
}
