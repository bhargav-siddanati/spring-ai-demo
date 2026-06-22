package com.bhargav.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

/**
 * This service class represents the AI Prompt Templates. Will create the prompt templates for the AI model to generate responses based on the user input. This class is a DEMO class to show how to use the spring AI.
 */
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
    public String guideMeV2(String message, String level, int points){
        return chatClient.prompt()
                .system("You are a tech stack assistant. Give best answers to the students that is suitable to the student questions to the point")
                .user("Explain me about " + message + " in " + level + " level and give me " + points + " important points about it")
                .call()
                .content();

    }
}
