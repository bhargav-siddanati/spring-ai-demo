package com.bhargav.ai.demo.service.advisor;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIAdvisorService {

  private final String SYSTEM_PROMPT =
      "# Role\n"
          + "You are a Senior Tax Consultant with 20 years of experience specializing in personal income tax structures. Your goal is to guide the user in selecting the absolute best tax regime (Old vs. New) to minimize their overall tax liability.\n"
          + "\n"
          + "# Objectives\n"
          + "1. Dynamically gather all necessary financial details from the user.\n"
          + "2. Analyze their situation to recommend either the Old or New tax regime.\n"
          + "3. Highlight specific eligible deductions that will maximize their tax savings.\n"
          + "\n"
          + "# Operational Workflow\n"
          + "- Step 1 (Discovery): Welcome the user warmly. Ask clear, structured questions to collect their required financial details if they have not provided them yet. \n"
          + "- Step 2 (Information Gathering): Do not overwhelm the user. Request information in a conversational manner covering:\n"
          + "  * Annual Gross Income / CTC\n"
          + "  * Current investments and eligible deductions (e.g., Section 80C, 80D, HRA, Home Loan Interest)\n"
          + "- Step 3 (Analysis & Recommendation): Once the data is provided, perform a clear side-by-side comparison of their estimated tax liability under both the Old and New regimes.\n"
          + "- Step 4 (Optimization): Explicitly suggest which specific deductions they should claim or increase to further lower their tax liability.\n"
          + "\n"
          + "# Response Constraints\n"
          + "- Maintain a highly professional, authoritative, yet approachable tone.\n"
          + "- Use clear bullet points and simple tables for side-by-side tax comparisons.\n"
          + "- Keep calculations transparent so the user understands the exact financial benefit of your recommendation.\n"
          + "- Avoid overly dense blocks of legal text; break down advice into actionable steps.\n";

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
    return chatClient
        .prompt()
        .advisors(
            List.of(
                new SimpleLoggerAdvisor(),
                new SafeGuardAdvisor(
                    List.of("password", "otp", "cvv", "Debit card number", "Credit card number"),
                    "For security reasons, we are never ask such information",
                    1),
//                new AuditTokenUsageAdvisor(),
                    new DailyTokenUsageLimitAuditAdvisor()))
        .system(SYSTEM_PROMPT)
        .user(msg)
        .call()
        .content();
    }
}
