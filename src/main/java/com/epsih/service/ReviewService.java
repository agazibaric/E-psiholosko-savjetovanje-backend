package com.epsih.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epsih.dto.ReviewDto;
import com.epsih.exceptions.NotFoundException;
import com.epsih.model.Review;
import com.epsih.model.User;
import com.epsih.repository.ReviewRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewService {
	
	private final ReviewRepository repository;
	
	private final UserService userService;
	
	public boolean contains(Long id) {
		return repository.findById(id).isPresent();
	}
	
	public List<Review> allReviews(){
		return repository.findAll();
	}
	
	public Review reviewById(Long id){
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Review with give id not found"));
	}
	
	public void addReview(ReviewDto dto) {
		User currentUser = userService.getUserWithAuthorities().get();
		User target = userService.getById(dto.getTargetId());
		Review review = Review.builder().
				               grade(dto.getGrade()).
				               target(target).
				               reviewer(currentUser).
				               build();
		repository.save(review);
	}
	
	public void deleteReview(Long id) {
		repository.deleteById(id);
	}

}
