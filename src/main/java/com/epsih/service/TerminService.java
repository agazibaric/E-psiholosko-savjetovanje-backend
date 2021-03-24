package com.epsih.service;

import java.util.List;
import java.util.Optional;

import com.epsih.dto.TerminDto;
import com.epsih.exceptions.BadRequestException;
import com.epsih.repository.MeetingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.Termin;
import com.epsih.repository.TerminRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TerminService {

   private final TerminRepository repository;
   private final MeetingRepository meetingRepository;

   public boolean contains(Long id) {
      return repository.findById(id).isPresent();
   }

   public List<Termin> allTermins() {
      return repository.findAll();
   }

   public Termin terminById(Long id) {
      return repository.findById(id).orElseThrow(() -> new NotFoundException("Termin with give id not found"));
   }

   public void addTermin(TerminDto terminDto) {
      // TODO Additional check:
      // If only doctor can create new termin, check that termin.meeting.doctor is currently sign in user
      if (terminDto.getTerminEnd().isBefore(terminDto.getTerminStart()))
         throw new BadRequestException("Scheduled termin time is not valid");

      Termin termin = Termin.builder()
         .terminStart(terminDto.getTerminStart())
         .terminEnd(terminDto.getTerminEnd())
         .terminType(terminDto.getTerminType())
         .meeting(meetingRepository.findById(terminDto.getMeetingId())
         .orElseThrow(() -> new NotFoundException("Meeting does no exist")))
         .build();
      repository.save(termin);
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
