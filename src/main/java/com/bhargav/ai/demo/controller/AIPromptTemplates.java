package com.bhargav.ai.demo.controller;

import com.bhargav.ai.demo.service.AIPromptTemplatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prompt-templates")
@RequiredArgsConstructor
public class AIPromptTemplates {
    private final AIPromptTemplatesService promptTemplatesService;

    @GetMapping("/check/guide")
    public ChatResponse checkGuide(@RequestParam String message){
        return promptTemplatesService.guideMe(message);
    }
    @GetMapping("/check/guide/v2")
    public String checkGuideV2(@RequestParam String message,
                               @RequestParam String level,
                               @RequestParam int points){
        return promptTemplatesService.guideMeV2(message, level, points);
    }
}
