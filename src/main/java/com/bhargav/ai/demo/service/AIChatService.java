package com.bhargav.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIChatService {

    /*private final ChatClient chatClient;

    public AIChatService (ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public String chatWithOllama(String message){
        return chatClient.prompt(message)
                .call()
                .content();
    }*/
}
