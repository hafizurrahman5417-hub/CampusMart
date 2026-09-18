package com.campusmart.controller;

import com.campusmart.model.Review;
import com.campusmart.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review addReview(
            @RequestBody Review review) {

        return reviewService.addReview(review);
    }

    @GetMapping("/product/{productId}")
    public List<Review> getReviews(
            @PathVariable Long productId) {

        return reviewService.getProductReviews(productId);
    }
}