package com.epsih.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.epsih.enums.MeetingType;
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
	@JoinColumn(name="fk_patient")
	@JsonIgnoreProperties("meetings")
	private Patient patient;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="fk_doctor")
	@JsonIgnoreProperties("meetings")
	private Doctor doctor;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="fk_termin")
	private Termin termin;
	
	@OneToOne
	@NotNull
	@JoinColumn(name="fk_service")
	@JsonIgnore
	private BusinessService service;
	
	@Column(name = "meeting_type")
	@Enumerated(EnumType.STRING)
	private MeetingType meetingType;
	
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Message> messages;
	
}
