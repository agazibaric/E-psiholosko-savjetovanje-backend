package com.epsih.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "USERS")
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
   @JsonIgnore
   @Id
   @Column(name = "ID")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "USERNAME", length = 50, unique = true)
   @NotNull
   @Size(min = 4, max = 50)
   private String username;

   @JsonIgnore
   @Column(name = "PASSWORD", length = 100)
   @NotNull
   @Size(min = 4, max = 100)
   private String password;

   @Column(name = "FIRSTNAME", length = 50)
   @NotNull
   @Size(min = 4, max = 50)
   private String firstname;

   @Column(name = "LASTNAME", length = 50)
   @NotNull
   @Size(min = 4, max = 50)
   private String lastname;

   @Column(name = "EMAIL", length = 50)
   @NotNull
   @Size(min = 4, max = 50)
   @Email
   private String email;

   // TODO: make phone number validator annotation
   @Column(name = "PHONENUMBER", length = 20)
   @NotNull
   private String phoneNumber;
   
   @JsonIgnore
   @Column(name = "ACTIVATED")
   @NotNull
   private boolean activated;

   @ManyToMany
   @JoinTable(
      name = "USER_AUTHORITY",
      joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "NAME")})
   @BatchSize(size = 20)
   private Set<Authority> authorities = new HashSet<>();

}

