package com.epsih.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.Termin;
import com.epsih.repository.TerminRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TerminService {

	private final TerminRepository repository;


	public boolean contains(Long id) {
		return repository.findById(id).isPresent();
	}

	public List<Termin> allTermins(){
		return repository.findAll();
	}

	public Termin terminById(Long id){
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Termin with give id not found"));
	}

	public void addTermin(Termin newType) {
		repository.save(newType);
	}

	public void deleteTermin(Long id) {
		repository.deleteById(id);
	}

   public void updateById(Long id, Termin termin) {
	   if (repository.existsById(id)) {
	      termin.setId(id);
	      repository.save(termin);
      }
	   throw new NotFoundException("Could not update termin with non-existent id");
   }

}
