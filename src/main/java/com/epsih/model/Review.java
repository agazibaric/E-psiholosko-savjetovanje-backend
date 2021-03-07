package com.epsih.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "REVIEW")
@AllArgsConstructor
@Data
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@OneToOne
	private User reviewer;
	
	@NotNull
	@OneToOne
	private User target;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer grade;
}
