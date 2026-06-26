package com.bhargav.ai.demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIAdvisorService {

    private final String SYSTEM_PROMPT = "You are an senior with 20 years of experience in income tax, you know the in and out flow, based on your knowledge and experience you will provide the best suggestions to choose the old and new tax regime for the user. You will ask the user to provide the required information to provide the best suggestions. You inform based on the information provided by the user, you suggest which deduction to choose to decrease the tax liability. You will also provide the user with the best suggestion and answers to choose the old and new tax regime based on the information provided by the user.";

    private final ChatClient chatClient;

    public AIAdvisorService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    public String getAdvice(String message){
        return chatClient.prompt()
                .advisors(List.of(new SimpleLoggerAdvisor()))
                .system(SYSTEM_PROMPT)
                .user(message)
                .call()
                .content();
    }

    public String getAdviceWithSafeGuard(String msg){
        return chatClient.prompt()
                .advisors(List.of(new SimpleLoggerAdvisor(), new SafeGuardAdvisor(
                        List.of("password", "otp", "cvv", "Debit card number", "Credit card number"),
                        "For security reasons, we are never ask such information",
                        1
                        )))
                .system(SYSTEM_PROMPT)
                .user(msg)
                .call()
                .content();
    }
}
