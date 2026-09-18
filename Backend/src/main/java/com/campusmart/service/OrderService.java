package com.campusmart.service;

import com.campusmart.model.*;
import com.campusmart.repository.CartRepository;
import com.campusmart.repository.OrderRepository;
import com.campusmart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Order placeOrder(Long userId, String pickupLocation) {

        List<CartItem> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();

        order.setUserId(userId);
        order.setPickupLocation(pickupLocation);

        List<OrderItem> orderItems = new ArrayList<>();

        double total = 0;

        for (CartItem cartItem : cartItems) {

            Product product = productRepository
                    .findById(cartItem.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem(
                    product.getId(),
                    product.getName(),
                    cartItem.getQuantity(),
                    cartItem.getPrice()
            );

            orderItems.add(orderItem);

            total += cartItem.getPrice() * cartItem.getQuantity();

            if (product.getStock() != null) {
                product.setStock(
                        Math.max(0,
                                product.getStock() - cartItem.getQuantity())
                );

                productRepository.save(product);
            }
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        cartRepository.deleteByUserId(userId);

        return savedOrder;
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateStatus(Long id, String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }
}