package com.epsih.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "TERMIN")
@AllArgsConstructor
@Data
public class Termin {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private Instant terminStart;
	@NotNull
	private Instant terminEnd;
	private String description;
}
