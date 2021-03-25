package com.epsih.service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.exceptions.ServerErrorException;
import com.epsih.exceptions.UnauthorizedException;
import com.epsih.model.Doctor;
import com.epsih.model.Meeting;
import com.epsih.model.Review;
import com.epsih.model.Termin;
import com.epsih.repository.DoctorRepository;
import com.epsih.repository.MeetingRepository;
import com.epsih.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {

   private final DoctorRepository doctorRepository;
   private final MeetingRepository meetingRepository;

   public Doctor getCurrentDoctor() {
      return SecurityUtils.getCurrentUsername()
         .flatMap(doctorRepository::findOneByUser_Username)
         .orElseThrow(() -> new ServerErrorException("Something went wrong!"));
   }

   public List<Meeting> getMyMeetings() {
      return getCurrentDoctor().getMeetings();
   }

   public Meeting getMyMeeting(Long id) {
      Meeting meeting = meetingRepository.findById(id)
         .orElseThrow(() -> new NotFoundException("Meeting does not exist"));
      if (!meeting.getDoctor().getId().equals(getCurrentDoctor().getId()))
         throw new UnauthorizedException("Unauthorized to access the resource");
      return meeting;
   }

   public List<Termin> getMeetingTermins(Long meetingId) {
      Meeting meeting = meetingRepository.findById(meetingId)
         .orElseThrow(() -> new NotFoundException("Meeting does not exist"));
      if (!meeting.getDoctor().getId().equals(getCurrentDoctor().getId()))
         throw new UnauthorizedException("Unauthorized to access the resource");
      return meeting.getTermins();
   }

   public List<Review> getMyReviews() {
      return getCurrentDoctor().getReviews();
   }

}
