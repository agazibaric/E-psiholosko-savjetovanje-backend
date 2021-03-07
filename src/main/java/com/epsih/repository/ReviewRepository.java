package com.epsih.repository;

import com.epsih.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.epsih.model.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

   List<Review> findByReviewer(User reviewer);

}
