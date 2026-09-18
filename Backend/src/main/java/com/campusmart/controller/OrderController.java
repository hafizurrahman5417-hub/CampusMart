package com.campusmart.controller;

import com.campusmart.model.Order;
import com.campusmart.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(
            @RequestBody Map<String, Object> data) {

        Long userId = Long.valueOf(
                data.get("userId").toString()
        );

        String pickupLocation =
                data.get("pickupLocation").toString();

        return orderService.placeOrder(
                userId,
                pickupLocation
        );
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(
            @PathVariable Long userId) {

        return orderService.getUserOrders(userId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> data) {

        return orderService.updateStatus(
                id,
                data.get("status")
        );
    }
}