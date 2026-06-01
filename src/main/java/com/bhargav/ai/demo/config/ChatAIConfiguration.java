package com.bhargav.ai.demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Here, we created two separate ChatClient beans, one for OpenAI and another for Ollama. Each ChatClient is configured with its respective model. This allows us to inject the appropriate ChatClient into our service layer based on which AI model we want to interact with.
 */
@Configuration
public class ChatAIConfiguration {
    @Bean
    public ChatClient openAIChatClient(OpenAiChatModel openAIChatModel){
        return ChatClient.builder(openAIChatModel).build();
    }
    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel){
        return ChatClient.builder(ollamaChatModel).build();
    }
}
