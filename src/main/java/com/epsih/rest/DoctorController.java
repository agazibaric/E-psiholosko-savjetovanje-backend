package com.epsih.rest;

import com.epsih.constants.Endpoints;
import com.epsih.model.Doctor;
import com.epsih.model.Meeting;
import com.epsih.model.Termin;
import com.epsih.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(Endpoints.DOCTOR_ROOT)
public class DoctorController {

   private final DoctorService doctorService;

   @GetMapping(Endpoints.DOCTOR_ME)
   public Doctor getCurrentDoctor() {
      return doctorService.getCurrentDoctor();
   }

   @GetMapping(Endpoints.DOCTOR_MEETINGS)
   public List<Meeting> getMyMeetings() {
      return doctorService.getMyMeetings();
   }

   @GetMapping(Endpoints.DOCTOR_MEETING_ID)
   public Meeting getMyMeetingById(@PathVariable("id") Long id) {
      return doctorService.getMyMeeting(id);
   }

   @GetMapping(Endpoints.DOCTOR_MEETING_TERMINS)
   public List<Termin> getTerminsForMeeting(@PathVariable("id") Long meetingId) {
      return doctorService.getMeetingTermins(meetingId);
   }

}
