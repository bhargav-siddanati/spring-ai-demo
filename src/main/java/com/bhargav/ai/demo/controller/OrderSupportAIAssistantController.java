package com.bhargav.ai.demo.controller;

import com.bhargav.ai.demo.service.OrderSupportAIAssistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-support")
public class OrderSupportAIAssistantController {
    private OrderSupportAIAssistanceService service;
    public OrderSupportAIAssistantController(OrderSupportAIAssistanceService service){
        this.service = service;
    }
    @GetMapping("/assist")
    public String getOrderSupportAssistance(@RequestParam String customerName,
                                            @RequestParam String orderId,
                                            @RequestParam String customerMessage){
        return service.assistWithOrderSupport(customerName, orderId, customerMessage);
    }
}
