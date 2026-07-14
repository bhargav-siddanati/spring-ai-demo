package com.bhargav.ai.demo.controller.messageroles;

import com.bhargav.ai.demo.service.messageroles.MessageRolesDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message-roles")
@RequiredArgsConstructor
public class AIChatMessageRoleController {
    private final MessageRolesDemoService messageRolesService;

    @GetMapping("/check/policy")
    public String checkPolicy(@RequestParam String message){
        return messageRolesService.chatWithRolesV3(message);
    }
}
