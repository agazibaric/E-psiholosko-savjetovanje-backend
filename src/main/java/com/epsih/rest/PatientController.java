package com.epsih.rest;

import com.epsih.constants.Endpoints;
import com.epsih.model.Meeting;
import com.epsih.model.Patient;
import com.epsih.model.Review;
import com.epsih.model.Termin;
import com.epsih.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(Endpoints.PATIENT_ROOT)
public class PatientController {

   private final PatientService patientService;

   @GetMapping(Endpoints.PATIENT_ME)
   public Patient getCurrentDoctor() {
      return patientService.getCurrentPatient();
   }

   @GetMapping(Endpoints.PATIENT_MEETINGS)
   public List<Meeting> getMyMeetings() {
      return patientService.getMyMeetings();
   }

   @GetMapping(Endpoints.PATIENT_MEETING_ID)
   public Meeting getMyMeetingById(@PathVariable("id") Long id) {
      return patientService.getMyMeeting(id);
   }

   @GetMapping(Endpoints.PATIENT_MEETING_TERMINS)
   public List<Termin> getTerminsForMeeting(@PathVariable("id") Long meetingId) {
      return patientService.getMeetingTermins(meetingId);
   }

   @GetMapping(Endpoints.PATIENT_REVIEWS)
   public List<Review> getMyReviews() {
      return patientService.getMyReviews();
   }

}
