package com.bhargav.ai.demo.service.ordersupport;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderSupportAIAssistanceService {

    private final ChatClient chatClient;

    @Value("classpath:prompts/order_system_template.st")
    private Resource orderSystemTemplate;

    @Value("classpath:prompts/order_user_template.st")
    private Resource orderUserTemplate;

    @Value("classpath:prompts/order_system_policy.st")
    private Resource orderSystemPolicyTemplate;

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

    public String assistantWithOrderSupportV2(String customerName, String OrderId, String customerMessage){
        return chatClient.prompt()
                .system(orderSystemTemplate)
                .user(promptUserSpec -> promptUserSpec.text(orderUserTemplate)
                        .param("customerName", customerName)
                        .param("orderId", OrderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();
    }

    public String assistantWithOrderSupportV3(String customerName, String OrderId, String customerMessage){
        return chatClient.prompt()
                .system(orderSystemPolicyTemplate)
                .user(promptUserSpec -> promptUserSpec.text(orderUserTemplate)
                        .param("customerName", customerName)
                        .param("orderId", OrderId)
                        .param("customerMessage", customerMessage))
                .call()
                .content();
    }

}
