package com.epsih.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epsih.dto.ReviewDto;
import com.epsih.exceptions.NotFoundException;
import com.epsih.model.Doctor;
import com.epsih.model.Patient;
import com.epsih.model.Review;
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
      Patient currentUser = (Patient) userService.getUserWithAuthorities().get();
      Doctor target = (Doctor) userService.getById(dto.getTargetId());
      // TODO: check that target is has ROLE_SPECIAL_USER
      
      Review review = Review.builder()
         .grade(dto.getGrade())
         .doctor(target)
         .patient(currentUser)
         .description(dto.getDescription())
         .build();
      reviewRepository.save(review);
   }

   public void updateById(Long id, ReviewDto dto) {
//      Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Review with given ID not found"));
//      User target = userService.getById(dto.getTargetId());
//      // TODO: allow to update the target user ?
//      review.setTarget(target);
//      review.setGrade(dto.getGrade());
//      review.setDescription(dto.getDescription());
   }

   public void deleteReview(Long id) {
      reviewRepository.deleteById(id);
   }

   public List<Review> getMyReviews() {
      Patient currentUser = (Patient) userService.getUserWithAuthorities().get();
      return null;
      //return reviewRepository.findByReviewer(currentUser);
   }
}