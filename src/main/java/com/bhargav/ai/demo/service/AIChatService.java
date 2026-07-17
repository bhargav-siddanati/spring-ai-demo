package com.bhargav.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class AIChatService {

  private final ChatClient chatClient;

  public AIChatService(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  public String chatWithOllama(String message) {
    return chatClient.prompt(message).call().content();
  }

  public String chatOptions(String msg) {
    ChatOptions options =
        ChatOptions.builder()
            .temperature(0.3)
            //                .maxTokens(10)
            .frequencyPenalty(0.7)
            .presencePenalty(0.7)
            .stopSequences(List.of("}"))
            .topK(50)
            .topP(0.5)
            .build();
    return chatClient.prompt(msg).options(options).call().content();
  }

  public Flux<String> chatOptionsWithFlux(String msg) {
    ChatOptions options =
        ChatOptions.builder()
            .temperature(0.3)
            //                .maxTokens(10)
            .frequencyPenalty(0.7)
            .presencePenalty(0.7)
            .stopSequences(List.of("}"))
            .topK(50)
            .topP(0.5)
            .build();
    return chatClient.prompt(msg).options(options).stream().content();
  }
}
