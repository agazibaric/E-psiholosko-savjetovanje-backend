package com.epsih.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.model.BusinessCategory;
import com.epsih.service.BusinessCategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryContoller {

	private final BusinessCategoryService service;

	@GetMapping("/{id}")
	public ResponseEntity<BusinessCategory> getCategoryById(@PathVariable Long id) {
		return new ResponseEntity<>(service.businessCategoryById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<BusinessCategory>> categories() {
		return new ResponseEntity<>(service.allBusinessCategories(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> postNewCategory(@Valid @RequestBody BusinessCategory category) {
		service.addNewBusinessCategory(category);
	   return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		service.deleteBusinessCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BusinessCategory> updateBusinessType(@PathVariable Long id, @RequestBody BusinessCategory category) {
		service.updateById(id, category);
		BusinessCategory updated = service.businessCategoryById(id);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

}
