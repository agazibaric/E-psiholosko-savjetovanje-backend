package com.epsih.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="patient")
public class Patient extends User{
	
   private String diagnosis;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient", cascade = CascadeType.ALL)
   @JsonIgnore
   private Set<Meeting> meetings;
}
