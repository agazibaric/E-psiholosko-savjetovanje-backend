package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.meeting.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
