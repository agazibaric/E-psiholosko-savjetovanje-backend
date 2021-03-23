package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
