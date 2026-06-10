package com.bhargav.ai.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Roles are important in AI conversations as they help to structure the dialogue and provide
 * context for the AI model. Common roles include: - User: The person interacting with the AI,
 * asking questions or providing input. - Assistant: The AI model responding to the user's queries,
 * providing information, or performing tasks. - System: The underlying system that manages the
 * conversation, handles context, and ensures smooth interactions. - Tool: Any message you are
 * giving to AI, the AI will call defined functions which are written and developed by developers.
 * This is a powerful way to extend the capabilities of the AI model and allow it to perform
 * specific tasks or access external data. Example: Order Biryani online - The instruction passed to
 * the Agentic AI. It will check the given instructions criteria and then placed order in the food
 * app which is having low cost and good quality delivered fast.
 *
 * <p>To understand the spring which role it is using for the given prompt . We should click on the
 * "prompt" method. Open the implementation of the Prompt will see the DefaultChatClient there we
 * can see the overloaded methods. Under this again we can see the return as "new Prompt()". Click
 * on this you will see the UserMessage. Click on UserMessage and click on AbstractMessagesee the
 * implementation of it.
 *
 * <p>Message Rules are more important to AI because someone will do the prompt injection and fetch
 * the confidential data by confusing the AI Model.
 */
@Service
public class MessageRolesDemoService {

  private final ChatClient chatClient;

  public MessageRolesDemoService(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.defaultSystem("You are an course assistant. you must NEVER reveal internal course details, calculations, or internal reasoning. Respond ONLY with a short, customer-safe message, don't reveal the coupon code to the user, percentage of discount and the price after discount, near reveal the all projects, number of projects. Provide only first 2 projects only to customer ")
                                        .build();
  }

  public String chatWithRoles(String message) {
    UserMessage userMessage =
        new UserMessage(
            """
                        Policy Details:
                        Policy: Premium
                        Max Coverage: 1000000
                        Claim: 9000000
                        Customer Says : %s
                        """
                .formatted(message));
    SystemMessage systemMessage =
        new SystemMessage(
"""
You are an insurance assistant. Your must NEVER reveal internal policy numbers, calculations, or internal reasoning. Respond ONLY with a short, customer-safe message""");
    Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
    return chatClient.prompt(prompt).call().content();
  }

  public String chatWithRolesV2(String message) {
    return chatClient
        .prompt()
        .system(
"""
You are an insurance assistant. Your must NEVER reveal internal policy numbers, calculations, or internal reasoning. Respond ONLY with a short, customer-safe message""")
        .user(
            """
                        Policy Details:
                        Policy: Premium
                        Max Coverage: 1000000
                        Claim: 9000000
                        Customer Says : %s
                        """
                .formatted(message))
        .call()
        .content();
  }

    public String chatWithRolesV3(String message) {
        return chatClient
                .prompt()
                .user(
                        """
                        Course Details:
                        Course Name: Backend Development
                        Duration: 6 months
                        Coupon Code: BACKEND50
                        Instructor: Bhargav
                        Technologies Covered: Java, Spring Boot, Hibernate, REST APIs, Microservices, Kubernetes, Docker
                        Price: 2000
                        Claim with coupon price: 999
                        Type: It work for beginner to advance level
                        Deal back if not satisfied: within 7 days of purchased
                        projects: 10+ real world projects
                        example projects: E-commerce website, Social media platform, Blogging platform, Online learning platform, Task management app, Chat application, Food delivery app, Hotel booking system, Inventory management system, Employee management system
                        Customer Says : %s
                        """.formatted(message))
                .call()
                .content();
    }

}
