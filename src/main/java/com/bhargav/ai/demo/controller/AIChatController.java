package com.bhargav.ai.demo.controller;

import com.bhargav.ai.demo.service.AIChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ai/api")
public class AIChatController {

    private final AIChatService service;

    public AIChatController(AIChatService service){
        this.service = service;
    }

    @GetMapping("/chat")
    public String chatWithAI(@RequestParam String message){
        return service.chatWithOllama(message);
    }
}
