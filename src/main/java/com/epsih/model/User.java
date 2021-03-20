package com.epsih.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
	
   @JsonIgnore
   @Id
   @Column(name = "pk_user")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 50, unique = true)
   @NotNull
   @Size(min = 4, max = 50)
   private String username;

   @JsonIgnore
   @Column(length = 100)
   @NotNull
   @Size(min = 4, max = 100)
   private String password;

   @Column(length = 50)
   @NotNull
   @Size(min = 4, max = 50)
   private String firstname;

   @Column(length = 50)
   @NotNull
   @Size(min = 4, max = 50)
   private String lastname;

   @Column(length = 50)
   @NotNull
   @Size(min = 4, max = 50)
   @Email
   private String email;

   // TODO: make phone number validator annotation
   @Column(name = "phonenumber", length = 20)
   @NotNull
   private String phoneNumber;
   
   @JsonIgnore
   @NotNull
   private boolean activated;

   @ManyToMany
   @JoinTable(
      name = "USER_AUTHORITY",
      joinColumns = @JoinColumn(name = "pk_user"),
      inverseJoinColumns = @JoinColumn(name = "name"))
   @BatchSize(size = 20)
   private Set<Authority> authorities = new HashSet<>();

}
