package com.epsih.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.service.BusinessCategory;
import com.epsih.repository.BusinessCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusinessCategoryService {
	private final BusinessCategoryRepository repository;

	public boolean contains(Long id) {
		Optional<BusinessCategory> type = repository.findById(id);
		return type.isPresent();
	}

	public List<BusinessCategory> allBusinessCategories(){
		return repository.findAll();
	}

	public BusinessCategory businessCategoryById(Long id){
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Business Category with given ID does not exists"));
	}

	public void addNewBusinessCategory(BusinessCategory newCategory) {
		repository.save(newCategory);
	}

	public void deleteBusinessCategory(Long id) {
		repository.deleteById(id);
	}

	public void updateById(Long id, BusinessCategory businessCategory) {
	   if (repository.existsById(id)) {
		   businessCategory.setId(id);
		   repository.save(businessCategory);
      } else {
	      throw new NotFoundException("Business Category with given Id does not exists");
      }
   }
}
