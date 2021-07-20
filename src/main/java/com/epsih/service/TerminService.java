package com.epsih.service;

import java.util.List;

import com.epsih.dto.TerminDto;
import com.epsih.exceptions.BadRequestException;
import com.epsih.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.meeting.Termin;
import com.epsih.repository.TerminRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TerminService {

   private final TerminRepository terminRepository;
   private final MeetingRepository meetingRepository;

   public boolean contains(Long id) {
      return terminRepository.findById(id).isPresent();
   }

   public List<Termin> allTermins() {
      return terminRepository.findAll();
   }

   public Termin terminById(Long id) {
      return terminRepository.findById(id).orElseThrow(() -> new NotFoundException("Termin with give id not found"));
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
      terminRepository.save(termin);
   }

   public void deleteTermin(Long id) {
      terminRepository.deleteById(id);
   }

   public void updateById(Long id, TerminDto terminDto) {
      if (terminDto.getTerminEnd().isBefore(terminDto.getTerminStart()))
         throw new BadRequestException("Scheduled termin time is not valid");

      Termin termin = terminRepository.findById(id)
         .orElseThrow(() -> new NotFoundException("Termin does not exist"));

      termin.setTerminStart(terminDto.getTerminStart());
      termin.setTerminEnd(terminDto.getTerminEnd());
      termin.setDescription(terminDto.getDescription());
      // Meeting update is not allowed
   }

}
