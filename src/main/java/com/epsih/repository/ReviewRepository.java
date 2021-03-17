package com.epsih.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsih.model.Review;
import com.epsih.model.User;

public interface ReviewRepository extends JpaRepository<Review, Long>{
   List<Review> findByReviewer(User reviewer);
}
