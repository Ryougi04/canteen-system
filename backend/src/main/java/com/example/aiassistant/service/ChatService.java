package com.example.aiassistant.service;

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

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", message);

            payload.put("messages", new org.json.JSONArray().put(userMessage));

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
            if (e.getStatusCode() == HttpStatus.PAYMENT_REQUIRED) {  // 402
                return "余额不足，请充值后重试。";
            }
            e.printStackTrace();
            return "请求出错：" + e.getStatusCode() + " " + e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return "内部错误：" + e.getMessage();
        }
    }
}
