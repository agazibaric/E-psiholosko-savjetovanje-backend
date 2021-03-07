package com.epsih.model;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TERMIN")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime terminStart;

	@NotNull
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime terminEnd;

	private String description;
}
