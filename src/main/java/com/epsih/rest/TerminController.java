package com.epsih.rest;

import java.util.List;

import javax.validation.Valid;

import com.epsih.constants.Endpoints;
import com.epsih.dto.TerminDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.model.meeting.Termin;
import com.epsih.service.TerminService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping(Endpoints.TERMIN_ROOT)
@AllArgsConstructor
@Data
public class TerminController {

   private final TerminService service;

   @GetMapping(Endpoints.TERMIN_ID)
   public Termin getTermin(@PathVariable Long id) {
      return service.terminById(id);
   }

   @GetMapping
   public List<Termin> getAllTermins() {
      return service.allTermins();
   }

   @PostMapping
   public void newTermin(@Valid @RequestBody TerminDto terminDto) {
      service.addTermin(terminDto);
   }

   @DeleteMapping(Endpoints.TERMIN_ID)
   public void deleteTermin(@PathVariable Long id) {
      service.deleteTermin(id);
   }

   @PutMapping(Endpoints.TERMIN_ID)
   public void updateTermin(@Valid @RequestBody TerminDto terminDto, @PathVariable Long id) {
      service.updateById(id, terminDto);
   }

}
