package com.epsih.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.epsih.enums.MeetingType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "meeting")
@AllArgsConstructor
@Data
public class Meeting {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "pk_meeting")
	private Long id;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="fk_patient")
	private Patient patient;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="fk_doctor")
	private Doctor doctor;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="fk_termin")
	private Termin termin;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="fk_service")
	private BusinessService service;
	
	@Column(name = "meeting_type")
	@Enumerated(EnumType.STRING)
	private MeetingType meetingType;
	
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> messages;
	
}
