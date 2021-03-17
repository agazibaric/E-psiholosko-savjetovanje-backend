package com.epsih.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.BusinessType;
import com.epsih.repository.BusinessTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusinessTypeService {

	@Autowired
	private final BusinessTypeRepository repository;

	public boolean contains(Long id) {
		Optional<BusinessType> type = repository.findById(id);
		return type.isPresent();
	}

	public List<BusinessType> allBusinessTypes() {
		return repository.findAll();
	}

	public BusinessType businessTypeById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Business with given ID does not exist"));
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
