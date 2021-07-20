package com.epsih.model.meeting;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.epsih.model.user.Doctor;
import com.epsih.model.user.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "pk_review")
   private Long id;

   @NotNull
   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "fk_patient")
   @JsonIgnoreProperties("reviews")
   private Patient patient;

   @NotNull
   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "fk_doctor")
   @JsonIgnoreProperties("reviews")
   private Doctor doctor;

   @NotNull
   @Min(1)
   @Max(5)
   private Integer grade;

   private String description;
}
