package com.bhargav.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class AIPromptTemplatesService {
    private final ChatClient chatClient;

    public AIPromptTemplatesService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public ChatResponse guideMe(String message) {
        return chatClient.prompt()
                .system("You are a tech stack assistant. Give best answers to the students that is suitable to the student questions to the point")
                .user(message)
                .call()
                .chatResponse();
    }
}
