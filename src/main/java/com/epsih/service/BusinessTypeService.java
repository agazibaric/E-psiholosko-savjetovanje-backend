package com.epsih.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsih.model.BusinessType;
import com.epsih.repository.BusinessServiceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusinessTypeService {
	@Autowired
	private final BusinessServiceRepository repository;
	
	public boolean contains(Long id) {
		Optional<BusinessType> type = repository.findById(id);
		return type.isPresent();
	}
	
	public List<BusinessType> allBusinessTypes(){
		return repository.findAll();
	}
	
	public Optional<BusinessType> businessTypeById(Long id){
		return repository.findById(id);
	}
	
	public void addNewBusinessType(BusinessType newType) {
		repository.save(newType);
	}
	
	public void deleteBusinessType(Long id) {
		repository.deleteById(id);
	}
	
	
}
