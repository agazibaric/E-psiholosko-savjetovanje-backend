package com.epsih.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
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
@Builder
public class User {

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
   @NotBlank(message = "Please enter your phone number")
   private String phoneNumber;

   @JsonIgnore
   @Column(columnDefinition = "boolean default false")
   private boolean activated;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
      name = "USER_AUTHORITY",
      joinColumns = @JoinColumn(name = "fk_user"),
      inverseJoinColumns = @JoinColumn(name = "name"))
   @BatchSize(size = 20)
   private Set<Authority> authorities = new HashSet<>();

}
