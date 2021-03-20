package com.epsih.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.BusinessService;
import com.epsih.repository.BusinessServiceRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class BusinessServiceService {

	private final BusinessServiceRepository repository;

	public boolean contains(Long id) {
		Optional<BusinessService> type = repository.findById(id);
		return type.isPresent();
	}

	public List<BusinessService> allBusinessTypes(){
		return repository.findAll();
	}

	public BusinessService businessTypeById(Long id){
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Business with given ID does not exists"));
	}

	public void addNewBusinessType(BusinessService newType) {
		repository.save(newType);
	}

	public void deleteBusinessType(Long id) {
		repository.deleteById(id);
	}

	public void updateById(Long id, BusinessService businessType) {
	   if (repository.existsById(id)) {
         businessType.setId(id);
         repository.save(businessType);
      } else {
	      throw new NotFoundException("Business with given Id does not exists");
      }
   }

}
