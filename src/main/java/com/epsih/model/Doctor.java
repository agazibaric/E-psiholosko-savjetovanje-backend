package com.epsih.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="doctor")
public class Doctor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne
   @JoinColumn(name = "fk_user", referencedColumnName = "pk_user")
   private User user;

	private String biography;

	@ManyToMany
	@JoinTable(name="doctor_service",
	           joinColumns = @JoinColumn(name="fk_doctor"),
	           inverseJoinColumns = @JoinColumn(name = "fk_service"))
	private Set<BusinessService> services;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL)
  @JsonIgnore
   private List<Meeting> meetings;
}
