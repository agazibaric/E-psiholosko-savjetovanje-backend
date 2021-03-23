package com.epsih.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name="doctor")
public class Doctor extends User {
	
	private String biography;

	@ManyToMany
	@JoinTable(name="doctor_service", 
	           joinColumns = @JoinColumn(name="pk_user"), 
	           inverseJoinColumns = @JoinColumn(name = "pk_business"))
	private Set<BusinessService> services;
	
  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
  @JsonIgnore
   private Set<Meeting> meetings;
}
