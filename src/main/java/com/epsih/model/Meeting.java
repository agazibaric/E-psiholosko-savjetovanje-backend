package com.epsih.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "MEETING")
@AllArgsConstructor
@Data
public class Meeting {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@OneToOne
	@NotNull
	private User patient;
	
	@OneToOne
	@NotNull
	private User doctor;
	
	@OneToOne
	@NotNull
	private Termin termin;
	
	@OneToOne
	@NotNull
	private BusinessType businessType;
	
	@Column(name = "TYPE")
	private String meetingType;
	
	@Column(name = "DESCRIPTION")
	private String description;
}
