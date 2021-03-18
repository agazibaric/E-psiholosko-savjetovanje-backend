package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsih.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
}
