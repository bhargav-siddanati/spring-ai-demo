package com.bhargav.ai.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultiModelChatService {
    private final ChatClient openAIChatClient;
    private final ChatClient ollamaChatClient;

    public String chatWithOpenAI(String message){
        return openAIChatClient.prompt(message)
                .call()
                .content();
    }

    public String chatWithOllamaAI(String message){
        return ollamaChatClient.prompt(message)
                .call()
                .content();
    }
}
