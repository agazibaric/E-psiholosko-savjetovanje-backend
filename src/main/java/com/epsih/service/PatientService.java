package com.epsih.service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.exceptions.ServerErrorException;
import com.epsih.model.Meeting;
import com.epsih.model.Patient;
import com.epsih.repository.PatientRepository;
import com.epsih.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

   private final PatientRepository patientRepository;

   public Patient getCurrentPatient() {
      return SecurityUtils.getCurrentUsername()
         .flatMap(patientRepository::findOneByUser_Username)
         .orElseThrow(() -> new ServerErrorException("Something went wrong!"));
   }

   public List<Meeting> getMyMeetings() {
      return getCurrentPatient().getMeetings();
   }

   public Meeting getMyMeeting(Long id) {
      return getMyMeetings().stream()
         .filter(m -> m.getId().equals(id))
         .findAny().orElseThrow(() -> new NotFoundException("Meeting does not exist"));
   }

}
