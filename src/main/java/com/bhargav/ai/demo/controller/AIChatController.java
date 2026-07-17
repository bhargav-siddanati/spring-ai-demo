package com.bhargav.ai.demo.controller;

import com.bhargav.ai.demo.service.AIChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @GetMapping("/chat-options")
    public String chatWithAIOptions(@RequestParam String msg){
        return service.chatOptions(msg);
    }
    @GetMapping(value = "/chat/Stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam String msg){
        return service.chatOptionsWithFlux(msg);
    }
}
