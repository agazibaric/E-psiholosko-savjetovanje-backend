package com.epsih.service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.exceptions.ServerErrorException;
import com.epsih.exceptions.UnauthorizedException;
import com.epsih.model.meeting.Meeting;
import com.epsih.model.user.Patient;
import com.epsih.model.meeting.Review;
import com.epsih.model.meeting.Termin;
import com.epsih.repository.MeetingRepository;
import com.epsih.repository.PatientRepository;
import com.epsih.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

   private final PatientRepository patientRepository;
   private final MeetingRepository meetingRepository;

   public Patient getCurrentPatient() {
      return SecurityUtils.getCurrentUsername()
         .flatMap(patientRepository::findOneByUser_Username)
         .orElseThrow(() -> new ServerErrorException("Something went wrong!"));
   }

   public List<Meeting> getMyMeetings() {
      return getCurrentPatient().getMeetings();
   }

   public Meeting getMyMeeting(Long id) {
      Meeting meeting = meetingRepository.findById(id)
         .orElseThrow(() -> new NotFoundException("Meeting does not exist"));
      if (!meeting.getPatient().getId().equals(getCurrentPatient().getId()))
         throw new UnauthorizedException("Unauthorized to access the resource");
      return meeting;
   }

   public List<Termin> getMeetingTermins(Long meetingId) {
      Meeting meeting = meetingRepository.findById(meetingId)
         .orElseThrow(() -> new NotFoundException("Meeting does not exist"));
      if (!meeting.getPatient().getId().equals(getCurrentPatient().getId()))
         throw new UnauthorizedException("Unauthorized to access the resource");
      return meeting.getTermins();
   }

   public List<Review> getMyReviews() {
      return getCurrentPatient().getReviews();
   }

}
