package com.epsih.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.epsih.enums.BusinessCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business_type", uniqueConstraints = @UniqueConstraint(columnNames = {"type", "name"}))
@Builder
public class BusinessType {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "pk_business")
   private Long id;

   @NotNull
   private String type;
   
   @NotNull
   @Enumerated(EnumType.STRING)
   private BusinessCategory category;
   
   @NotNull
   private String name;

   @NotNull
   private String description;
   
   @ManyToMany(mappedBy = "businessTypes")
   private Set<Doctor> doctors;
}