package com.epsih.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REVIEW")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private User reviewer;
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private User target;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Integer grade;
}
