package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.BusinessService;

@Repository
public interface BusinessServiceRepository extends JpaRepository<BusinessService, Long> {
}
