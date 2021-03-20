package com.epsih.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business_service", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "fk_category"}))
@Builder
public class BusinessService {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "pk_service")
   private Long id;

   @NotNull
   private String name;
   
   @NotNull
   private String description;
   
   @NotNull
   private Float price;
   
   @ManyToMany(mappedBy = "services")
   private Set<Doctor> doctors;
   
   @ManyToOne
   @JoinColumn(name = "fk_category")
   private BusinessCategory category;
}