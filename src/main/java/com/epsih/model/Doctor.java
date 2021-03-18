package com.epsih.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="doctor_business", 
	           joinColumns = @JoinColumn(name="pk_user"), 
	           inverseJoinColumns = @JoinColumn(name = "pk_business"))
	private Set<BusinessType> businessTypes;
}
