package com.epsih.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.model.BusinessService;
import com.epsih.service.BusinessServiceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/service")
public class ServiceContoller {

	private final BusinessServiceService service;

	@GetMapping("/{id}")
	public BusinessService getBusinessTypes(@PathVariable Long id) {
		return service.businessServiceById(id);
	}

	@GetMapping
	public List<BusinessService> getAllBusinessTypes() {
		return service.allBusinessServices();
	}

	@PostMapping
	public void postNewBusinessType(@Valid @RequestBody BusinessService businessService) {
	   service.addNewBusinessService(businessService);
	}

	@DeleteMapping("/{id}")
	public void deleteBusinessType(@PathVariable Long id) {
		service.deleteBusinessType(id);
	}

	@PutMapping("/{id}")
	public void updateBusinessType(@PathVariable Long id, @RequestBody BusinessService newType) {
		service.updateById(id, newType);
	}

}
