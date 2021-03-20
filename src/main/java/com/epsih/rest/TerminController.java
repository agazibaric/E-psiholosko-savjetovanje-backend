package com.epsih.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.model.Termin;
import com.epsih.service.TerminService;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/termin")
@AllArgsConstructor
@Data
public class TerminController {

	@Autowired
	private final TerminService service;

	@GetMapping("/{id}")
	public Termin getTermin(@PathVariable Long id) {
		return service.terminById(id);
	}

	@GetMapping
	public List<Termin> getAllTermins() {
		return service.allTermins();
	}

	@PostMapping
	public void newTermin(@Valid @RequestBody Termin termin) {
	   service.addTermin(termin);
	}

	@DeleteMapping("/{id}")
	public void deleteTermin(@PathVariable Long id) {
		service.deleteTermin(id);
	}

	@PutMapping("/{id}")
	public void updateTermin(@RequestBody Termin termin, @PathVariable Long id) {
		service.updateById(id, termin);
	}

}
