package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.BusinessType;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long>{


}
