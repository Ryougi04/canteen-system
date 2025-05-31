package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.json.JSONObject;

@Service
public class ChatService {

    @Value("${deepseek.api.key}")
    private String apiKey;

    public String chatWithModel(String message) {
        String url = "https://api.deepseek.com/v1/chat/completions";
        RestTemplate restTemplate = new RestTemplate();

        try {
            JSONObject payload = new JSONObject();
            payload.put("model", "deepseek-chat");

            // 添加 system 提示词
            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "你是一位专业的营养师，正在为一所学校的食堂服务。用户会向你咨询菜品营养、饮食建议、热量控制等问题。请结合营养知识，用通俗易懂、简洁明了的语言为用户提供科学的饮食建议。");

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", message);

            // 组合消息顺序：system -> user
            org.json.JSONArray messages = new org.json.JSONArray();
            messages.put(systemMessage);
            messages.put(userMessage);
            payload.put("messages", messages);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<String> entity = new HttpEntity<>(payload.toString(), headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                JSONObject body = new JSONObject(response.getBody());
                return body.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
            } else {
                return "获取大模型回复失败";
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.PAYMENT_REQUIRED) {
                return "余额不足，请充值后重试。";
            }
            e.printStackTrace();
            return "请求出错：";
        } catch (Exception e) {
            e.printStackTrace();
            return "内部错误：服务器繁忙！";
        }
    }
}
