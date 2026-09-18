package com.campusmart.controller;

import com.campusmart.model.Order;
import com.campusmart.model.Product;
import com.campusmart.repository.ProductRepository;
import com.campusmart.repository.UserRepository;
import com.campusmart.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;

    public AdminController(
            ProductRepository productRepository,
            UserRepository userRepository,
            OrderService orderService) {

        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @GetMapping("/products")
    public List<Product> products() {
        return productRepository.findAll();
    }

    @GetMapping("/users")
    public List<?> users() {

        return userRepository.findAll()
                .stream()
                .peek(user -> user.setPassword(null))
                .toList();
    }

    @GetMapping("/orders")
    public List<Order> orders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/stats")
    public Map<String, Long> stats() {

        return Map.of(
                "totalUsers",
                userRepository.count(),

                "totalProducts",
                productRepository.count(),

                "totalOrders",
                orderService.getAllOrders().stream().count()
        );
    }
}