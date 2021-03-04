package com.epsih.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="SERVICE")
public class BusinessType {
	public BusinessType() {}
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	@NotNull
	private String description;
	
}
