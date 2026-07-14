package com.bhargav.ai.demo.service.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditTokenUsageAdvisor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if(chatResponse != null){
            Usage usage = chatResponse.getMetadata().getUsage();
            if(usage != null){
                log.info("Token Usage: {}", usage.getTotalTokens());
                int inputTokens = usage.getPromptTokens();
                int outputTokens = usage.getCompletionTokens();
                int totalTokens = usage.getTotalTokens();
                log.info("Token Usage - Input Tokens: {}, Output Tokens: {}, Total Tokens: {}", inputTokens, outputTokens, totalTokens);
            }
        }
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "AuditTokenUsageAdvisor";
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
