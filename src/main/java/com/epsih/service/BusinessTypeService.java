package com.epsih.service;

import java.util.List;
import java.util.Optional;

import com.epsih.exceptions.NotFoundException;
import com.epsih.repository.BusinessTypeRepository;
import org.springframework.stereotype.Service;

import com.epsih.model.BusinessType;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class BusinessTypeService {

	private final BusinessTypeRepository repository;

	public boolean contains(Long id) {
		Optional<BusinessType> type = repository.findById(id);
		return type.isPresent();
	}

	public List<BusinessType> allBusinessTypes(){
		return repository.findAll();
	}

	public BusinessType businessTypeById(Long id){
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Business with given ID does not exists"));
	}

	public void addNewBusinessType(BusinessType newType) {
		repository.save(newType);
	}

	public void deleteBusinessType(Long id) {
		repository.deleteById(id);
	}

	public void updateById(Long id, BusinessType businessType) {
	   if (repository.existsById(id)) {
         businessType.setId(id);
         repository.save(businessType);
      } else {
	      throw new NotFoundException("Business with given Id does not exists");
      }
   }

}
