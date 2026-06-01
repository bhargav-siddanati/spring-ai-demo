package com.bhargav.ai.demo.controller;

import com.bhargav.ai.demo.service.MultiModelChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi-model/api")
@RequiredArgsConstructor
public class AIChatController {

    private final MultiModelChatService service;

    @GetMapping("/chat/openai")
    public String chatWithOpenAI(@RequestParam String message){
        return service.chatWithOpenAI(message);
    }

    @GetMapping("/chat/ollama")
    public String chatWithOllamaAI(@RequestParam String message){
        return service.chatWithOllamaAI(message);
    }
}
