package com.campusmart.controller;

import com.campusmart.model.Product;
import com.campusmart.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {

        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Product> search(
            @RequestParam String keyword) {

        return productService.searchProducts(keyword);
    }

    @GetMapping("/category/{category}")
    public List<Product> category(
            @PathVariable String category) {

        return productService.getByCategory(category);
    }

    @GetMapping("/type/{type}")
    public List<Product> type(
            @PathVariable String type) {

        return productService.getByType(type);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Product> seller(
            @PathVariable Long sellerId) {

        return productService.getBySeller(sellerId);
    }

    @PostMapping
    public Product addProduct(
            @RequestBody Product product) {

        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                java.util.Map.of("message",
                        "Product deleted successfully")
        );
    }
}