package com.epsih.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business_category")
@Builder
public class BusinessCategory {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "pk_category")
   private Long id;

   @NotNull
   private String name;
   
   @NotNull
   private String decription;
   
   @OneToMany(mappedBy = "category")
   private List<BusinessService> services;
   
   
}
