package com.bhargav.ai.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Roles are important in AI conversations as they help to structure the dialogue and provide context for the AI model. Common roles include:
 * - User: The person interacting with the AI, asking questions or providing input.
 * - Assistant: The AI model responding to the user's queries, providing information, or performing tasks.
 * - System: The underlying system that manages the conversation, handles context, and ensures smooth interactions.
 * - Tool: Any message you are giving to AI, the AI will call defined functions which are written and developed by developers. This is a powerful way to extend the capabilities of the AI model and allow it to perform specific tasks or access external data.
 * Example: Order Biryani online - The instruction passed to the Agentic AI. It will check the given instructions criteria and then placed order in the food app which is having low cost and good quality delivered fast.
 *
 * To understand the spring which role it is using for the given prompt . We should click on the "prompt" method. Open the implementation of the Prompt will see the DefaultChatClient there we can see the overloaded methods. Under this again we can see the return as "new Prompt()". Click on this you will see the UserMessage. Click on UserMessage and click on AbstractMessagesee the implementation of it.
 *
 * Message Rules are more important to AI because someone will do the prompt injection and fetch the confidential data by confusing the AI Model.
 *
 */
@Service
public class MessageRolesDemoService {

    private final ChatClient chatClient;

    public MessageRolesDemoService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String chatWithRoles(String message){
        UserMessage userMessage = new UserMessage("""
                        Policy Details:
                        Policy: Premium
                        Max Coverage: 1000000
                        Claim: 19000000
                        Customer Says : %s
                        """.formatted(message));
        Prompt prompt = new Prompt(List.of(userMessage));
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

}
