package com.epsih.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meeting {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   @Column(name = "pk_meeting")
   private Long id;

   @ManyToOne
   @NotNull
   @JoinColumn(name = "fk_patient")
   @JsonIgnoreProperties("meetings")
   private Patient patient;

   @ManyToOne
   @NotNull
   @JoinColumn(name = "fk_doctor")
   @JsonIgnoreProperties("meetings")
   private Doctor doctor;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "meeting", cascade = CascadeType.ALL)
   @JsonIgnoreProperties("meeting")
   private List<Termin> termins;

   @OneToOne
   @NotNull
   @JoinColumn(name = "fk_service")
   @JsonIgnore
   private BusinessService service;

   private String description;

   @OneToMany(mappedBy = "meeting", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
   @JsonIgnore
   private List<Message> messages;

   @OneToOne
   @JoinColumn(nullable = true, name = "fk_meeting_request")
   @JsonIgnore
   private MeetingRequest meetingRequest;

}
