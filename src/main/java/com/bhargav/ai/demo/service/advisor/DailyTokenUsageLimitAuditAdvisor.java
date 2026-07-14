package com.bhargav.ai.demo.service.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class DailyTokenUsageLimitAuditAdvisor implements CallAdvisor {
    private static final int DAILY_TOKEN_LIMIT = 1000;
    private final AtomicInteger aggregatedTokenUsage = new AtomicInteger(0);
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        if (aggregatedTokenUsage.get() >= DAILY_TOKEN_LIMIT) {
            log.warn("Request blocked. Token quota already exhausted.");
            return createQuotaDoneResponse("", chatClientRequest);
        }
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();
        if(chatResponse != null){
            Usage usage = chatResponse.getMetadata().getUsage();
            if(usage != null){
                int totalTokens = usage.getTotalTokens();
                aggregatedTokenUsage.addAndGet(totalTokens);
                log.info("Token Usage - Total Tokens: {}, Aggregated Token Usage: {}", totalTokens, aggregatedTokenUsage.get());
                if(aggregatedTokenUsage.get() >= DAILY_TOKEN_LIMIT){
                    log.warn("Request blocked. Token quota exhausted after this request.");
                    String partialResponse = "";
                    if(chatResponse.getResult() != null && chatResponse.getResult().getOutput() != null){
                        partialResponse = chatResponse.getResult().getOutput().getText();
                    }
                    return createQuotaDoneResponse(partialResponse, chatClientRequest);
                }
            }
        }
        return chatClientResponse;
    }

    private ChatClientResponse createQuotaDoneResponse(String partialResponse, ChatClientRequest chatClientRequest) {
        String finalMessage = partialResponse;
        if (partialResponse != null && !partialResponse.trim().isEmpty()) {
            finalMessage += "\n\nNote: Daily token usage limit reached. Further requests will be blocked until the next day.";
        } else {
            finalMessage = "Daily token usage limit reached. Further requests will be blocked until the next day.";
        }

        Generation quotaGeneration = new Generation(new AssistantMessage(finalMessage));
        ChatResponse quotaChatResponse = new ChatResponse(List.of(quotaGeneration));


        // Pass the new ChatResponse and preserve the existing map context (or fall back to an empty map)
        return new ChatClientResponse(
                quotaChatResponse,
                chatClientRequest.context() != null ? chatClientRequest.context() : Collections.emptyMap()
        );
    }

    @Override
    public String getName() {
        return "DailyTokenUsageLimitAuditAdvisor";
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
