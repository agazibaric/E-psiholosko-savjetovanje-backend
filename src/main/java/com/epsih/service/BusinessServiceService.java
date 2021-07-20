package com.epsih.service;

import java.util.List;
import java.util.Optional;

import com.epsih.model.user.Doctor;
import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.service.BusinessService;
import com.epsih.repository.BusinessServiceRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class BusinessServiceService {

   private final BusinessServiceRepository serviceRepository;

   public boolean contains(Long id) {
      Optional<BusinessService> service = serviceRepository.findById(id);
      return service.isPresent();
   }

   public List<BusinessService> allBusinessServices() {
      return serviceRepository.findAll();
   }

   public BusinessService businessServiceById(Long id) {
      return serviceRepository.findById(id).orElseThrow(() -> new NotFoundException("Business with given ID does not exists"));
   }

   public void addNewBusinessService(BusinessService businessService) {
      serviceRepository.save(businessService);
   }

   public void deleteBusinessType(Long id) {
      serviceRepository.deleteById(id);
   }

   public void updateById(Long id, BusinessService businessService) {
      if (serviceRepository.existsById(id)) {
         businessService.setId(id);
         serviceRepository.save(businessService);
      } else {
         throw new NotFoundException("Service with given Id does not exists");
      }
   }

   public List<Doctor> getServiceDoctors(Long serviceId) {
      return serviceRepository.findById(serviceId)
         .orElseThrow(() -> new NotFoundException("Service does not exist"))
         .getDoctors();
   }

}
