package com.epsih.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epsih.model.meeting.Termin;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminRepository extends JpaRepository<Termin, Long>{

}
