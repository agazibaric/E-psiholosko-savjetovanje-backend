package com.epsih.rest;

import java.util.List;

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
import com.epsih.model.Review;
import com.epsih.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
@Data
public class ReviewController {

	@Autowired
	private final ReviewService reviewService;

	@GetMapping("/{id}")
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

	@DeleteMapping("/{id}")
	public void deleteReview(@PathVariable Long id) {
      reviewService.deleteReview(id);
	}

	@PutMapping("/{id}")
	public void updateReview(@RequestBody ReviewDto dto, @PathVariable Long id) {
      reviewService.updateById(id, dto);
	}

   @GetMapping("/myReviews")
	public List<Review> getMyReviews() {
	   return reviewService.getMyReviews();
   }

}
