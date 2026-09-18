package com.campusmart.service;

import com.campusmart.model.CartItem;
import com.campusmart.model.Product;
import com.campusmart.repository.CartRepository;
import com.campusmart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public CartItem addToCart(Long userId, Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartRepository
                .findByUserIdAndProductId(userId, productId)
                .orElse(new CartItem());

        item.setUserId(userId);
        item.setProductId(productId);
        item.setPrice(product.getPrice());

        if (item.getQuantity() == null) {
            item.setQuantity(quantity);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        return cartRepository.save(item);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}