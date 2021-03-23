package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
