package com.epsih.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BUSINESSTYPE", uniqueConstraints = @UniqueConstraint(columnNames = {"type", "name"}))
@Builder
public class BusinessType {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   @NotNull
   private String type;

   @Column
   @NotNull
   private String name;

   @Column
   @NotNull
   private String description;

}
