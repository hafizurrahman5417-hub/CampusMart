package com.campusmart.repository;

import com.campusmart.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductIdOrderByReviewDateDesc(Long productId);

    List<Review> findByUserId(Long userId);
}