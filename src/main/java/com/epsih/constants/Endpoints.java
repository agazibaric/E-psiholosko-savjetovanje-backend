package com.epsih.constants;

public class Endpoints {

   public static final String API_PREFIX = "/api";

   // UserController
   public static final String USER_ROOT = API_PREFIX + "/user";
   public static final String USER_ME = "/me";
   public static final String USER_ID = "/{id}";

   // PatientController
   public static final String PATIENT_ROOT = API_PREFIX + "/patient";
   public static final String PATIENT_ME = "/me";
   public static final String PATIENT_MEETINGS = "/meetings";
   public static final String PATIENT_MEETING_ID = "/meetings/{id}";
   public static final String PATIENT_MEETING_TERMINS = "/meetings/{id}/termins";

   // DoctorController
   public static final String DOCTOR_ROOT = API_PREFIX + "/doctor";
   public static final String DOCTOR_ME = "/me";
   public static final String DOCTOR_MEETINGS = "/meetings";
   public static final String DOCTOR_MEETING_ID = "/meetings/{id}";
   public static final String DOCTOR_MEETING_TERMINS = "/meetings/{id}/termins";

}
