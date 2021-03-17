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

import com.epsih.model.BusinessType;
import com.epsih.service.BusinessTypeService;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/business")
public class BusinessTypeContoller {

	private final BusinessTypeService service;

	@GetMapping("/{id}")
	public BusinessType getBusinessTypes(@PathVariable Long id) {
		return service.businessTypeById(id);
	}

	@GetMapping
	public List<BusinessType> getAllBusinessTypes() {
		return service.allBusinessTypes();
	}

	@PostMapping
	public void postNewBusinessType(@Valid @RequestBody BusinessType newType) {
	   service.addNewBusinessType(newType);
	}

	@DeleteMapping("/{id}")
	public void deleteBusinessType(@PathVariable Long id) {
		service.deleteBusinessType(id);
	}

	@PutMapping("/{id}")
	public void updateBusinessType(@PathVariable Long id, @RequestBody BusinessType newType) {
		newType.setId(id);
		service.addNewBusinessType(newType);
	}

}