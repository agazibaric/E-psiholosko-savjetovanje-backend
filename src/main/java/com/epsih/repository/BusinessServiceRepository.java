package com.epsih.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.BusinessType;

@Repository
public interface BusinessServiceRepository extends JpaRepository<BusinessType, Long>{


}
