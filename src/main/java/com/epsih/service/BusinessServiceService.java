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
      Optional<BusinessService> service = repository.findById(id);
      return service.isPresent();
   }

   public List<BusinessService> allBusinessServices() {
      return repository.findAll();
   }

   public BusinessService businessServiceById(Long id) {
      return repository.findById(id).orElseThrow(() -> new NotFoundException("Business with given ID does not exists"));
   }

   public void addNewBusinessService(BusinessService businessService) {
      repository.save(businessService);
   }

   public void deleteBusinessType(Long id) {
      repository.deleteById(id);
   }

   public void updateById(Long id, BusinessService businessService) {
      if (repository.existsById(id)) {
         businessService.setId(id);
         repository.save(businessService);
      } else {
         throw new NotFoundException("Service with given Id does not exists");
      }
   }

}
