package com.epsih.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.Doctor;
import com.epsih.model.Meeting;
import com.epsih.model.Patient;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>{
	List<Meeting> findByDoctor(Doctor doctor);	
	List<Meeting> findByPatient(Patient patient);
	
}
