package com.campusmart.controller;

import com.campusmart.model.CartItem;
import com.campusmart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(
            @PathVariable Long userId) {

        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public CartItem addToCart(
            @RequestBody Map<String, Object> data) {

        Long userId = Long.valueOf(
                data.get("userId").toString()
        );

        Long productId = Long.valueOf(
                data.get("productId").toString()
        );

        Integer quantity = Integer.valueOf(
                data.get("quantity").toString()
        );

        return cartService.addToCart(
                userId,
                productId,
                quantity
        );
    }

    @DeleteMapping("/{id}")
    public Map<String, String> remove(
            @PathVariable Long id) {

        cartService.removeFromCart(id);

        return Map.of(
                "message",
                "Item removed from cart"
        );
    }

    @DeleteMapping("/clear/{userId}")
    public Map<String, String> clear(
            @PathVariable Long userId) {

        cartService.clearCart(userId);

        return Map.of(
                "message",
                "Cart cleared"
        );
    }
}