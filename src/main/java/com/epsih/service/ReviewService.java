package com.epsih.service;

import java.util.List;

import com.epsih.dto.ReviewDto;
import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.Review;
import com.epsih.model.User;
import com.epsih.repository.ReviewRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewService {

   private final ReviewRepository reviewRepository;

   private final UserService userService;

   public boolean contains(Long id) {
      return reviewRepository.findById(id).isPresent();
   }

   public List<Review> allReviews() {
      return reviewRepository.findAll();
   }

   public Review reviewById(Long id) {
      return reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Review with give id not found"));
   }

   public void addReview(ReviewDto dto) {
      User currentUser = userService.getUserWithAuthorities().get();
      User target = userService.getById(dto.getTargetId());
      // TODO: check that target is has ROLE_SPECIAL_USER
      Review review = Review.builder()
         .grade(dto.getGrade())
         .target(target)
         .description(dto.getDescription())
         .reviewer(currentUser)
         .build();
      reviewRepository.save(review);
   }

   public void updateById(Long id, ReviewDto dto) {
      Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Review with given ID not found"));
      User target = userService.getById(dto.getTargetId());
      // TODO: allow to update the target user ?
      review.setTarget(target);
      review.setGrade(dto.getGrade());
      review.setDescription(dto.getDescription());
   }

   public void deleteReview(Long id) {
      reviewRepository.deleteById(id);
   }

   public List<Review> getMyReviews() {
      User currentUser = userService.getUserWithAuthorities().get();
      return reviewRepository.findByReviewer(currentUser);
   }
}
