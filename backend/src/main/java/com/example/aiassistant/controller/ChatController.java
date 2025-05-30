package com.example.aiassistant.controller;

import com.example.aiassistant.entity.ChatRequest;
import com.example.aiassistant.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String getChatResponse(@RequestBody ChatRequest request) {
        return chatService.chatWithModel(request.getMessage());
    }
}
