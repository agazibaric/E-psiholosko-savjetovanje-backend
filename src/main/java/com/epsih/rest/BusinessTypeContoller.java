package com.epsih.rest;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.exceptions.NotFoundException;
import com.epsih.exceptions.DatabaseConstraints;
import com.epsih.model.BusinessType;
import com.epsih.service.BusinessTypeService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api/service")
@AllArgsConstructor
@Data
public class BusinessTypeContoller {
	
	@Autowired
	private final BusinessTypeService service;
	
	@GetMapping("/{id}")
	public BusinessType getBusinessTypes(@PathVariable Long id) {
		BusinessType type = null;
		try {
			 type = service.businessTypeById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException("The Service with given id not found");
		}
		return type;
	}
	
	@GetMapping
	public List<BusinessType> getAllBusinessTypes() {
		return service.allBusinessTypes();
	}
	
	@PostMapping
	public void postNewBusinessType(@RequestBody BusinessType newType) {
		try {
			service.addNewBusinessType(newType);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseConstraints("Added Business Type violates database unique constraint");
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteBusinessType(@PathVariable Long id) {
		service.deleteBusinessType(id);
	}
	
	@PutMapping("/{id}")
	public void updateBusinessType(@RequestBody BusinessType newType, @PathVariable Long id) {
		if(service.contains(id)) {
			newType.setId(id);
			service.addNewBusinessType(newType);
		}
		else {
			throw new NotFoundException("The Service with given id not found");
		}
	}
	
	
}
