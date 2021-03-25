package com.epsih.service;

import java.util.List;

import com.epsih.exceptions.ServerErrorException;
import com.epsih.repository.DoctorRepository;
import com.epsih.repository.PatientRepository;
import com.epsih.security.SecurityUtils;
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
   private final DoctorRepository doctorRepository;
   private final PatientRepository patientRepository;

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
      Patient currentUser = SecurityUtils.getCurrentUsername()
         .flatMap(patientRepository::findOneByUser_Username)
         .orElseThrow(() -> new ServerErrorException("Something went wrong when adding review!"));
      Doctor doctor = doctorRepository.findById(dto.getTargetId())
         .orElseThrow(() -> new NotFoundException("Doctor does not exist"));

      Review review = Review.builder()
         .grade(dto.getGrade())
         .doctor(doctor)
         .patient(currentUser)
         .description(dto.getDescription())
         .build();
      reviewRepository.save(review);
   }

   public void updateById(Long id, ReviewDto dto) {

   }

   public void deleteReview(Long id) {
      reviewRepository.deleteById(id);
   }

   public List<Review> getMyReviews() {
      return null;
   }
}
