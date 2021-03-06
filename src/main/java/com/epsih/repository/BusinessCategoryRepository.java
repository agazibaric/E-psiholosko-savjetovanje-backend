package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.service.BusinessCategory;

@Repository
public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long>{

}
