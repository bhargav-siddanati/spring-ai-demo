package com.bhargav.ai.demo.controller.advisor;

import com.bhargav.ai.demo.service.advisor.AIAdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advisor")
@RequiredArgsConstructor
public class AIAdvisorController {
    private final AIAdvisorService aiAdvisorService;

    @GetMapping("/getAdvice")
    public String getAdvice(String message){
        return aiAdvisorService.getAdvice(message);
    }

    @GetMapping("/getAdvice-safeGurad")
    public String getAdviceWithSafty(String message){
        return aiAdvisorService.getAdviceWithSafeGuard(message);
    }
}
