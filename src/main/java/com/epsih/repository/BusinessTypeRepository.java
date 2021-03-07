package com.epsih.repository;

import com.epsih.model.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long> {
}
