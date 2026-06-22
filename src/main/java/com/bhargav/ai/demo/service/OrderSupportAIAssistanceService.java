package com.bhargav.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OrderSupportAIAssistanceService {
    private final ChatClient chatClient;

    public OrderSupportAIAssistanceService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }
    public String assistWithOrderSupport(String customerName, String OrderId, String customerMessage){
        return chatClient.prompt()
                .system("You are a professional e-commerce customer support assistant. Your goal is to write clear, empathetic, and solution-oriented email responses. Never blame the customer or the company. Keep the response concise and friendly. Use the provided customer information and order details to generate accurate responses.")
                .user(promptUserSpec -> promptUserSpec.text("""
                         A customer named {customerName} contacted support regarding Order ID {orderId}.
                         
                         Customer Message:
                         "{customerMessage}"
                         
                         Your task:
                         - Understand the customer's issue clearly.
                         - Apologize if there is any inconvenience.
                         - Provide a helpful next step or resolution.
                         - Maintain a professional and polite tone.
                         - Do NOT include subject line or signature.""")
                        .param("customerName", customerName)
                        .param("orderId", OrderId)
                .param("customerMessage", customerMessage))
                .call()
                .content();
    }
}
