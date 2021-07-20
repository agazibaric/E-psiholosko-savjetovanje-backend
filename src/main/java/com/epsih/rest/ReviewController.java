package com.epsih.rest;

import java.util.List;

import com.epsih.constants.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.dto.ReviewDto;
import com.epsih.model.meeting.Review;
import com.epsih.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(Endpoints.REVIEW_ROOT)
@AllArgsConstructor
@Data
public class ReviewController {

	@Autowired
	private final ReviewService reviewService;

	@GetMapping(Endpoints.REVIEW_ID)
	public Review getReview(@PathVariable Long id) {
		return reviewService.reviewById(id);
	}

	@GetMapping
	public List<Review> getAllReviews() {
		return reviewService.allReviews();
	}

	@PostMapping
	public void newReview(@RequestBody ReviewDto dto) {
      reviewService.addReview(dto);
	}

	@DeleteMapping(Endpoints.REVIEW_ID)
	public void deleteReview(@PathVariable Long id) {
      reviewService.deleteReview(id);
	}

	@PutMapping(Endpoints.REVIEW_ID)
	public void updateReview(@RequestBody ReviewDto dto, @PathVariable Long id) {
      reviewService.updateById(id, dto);
	}

}
