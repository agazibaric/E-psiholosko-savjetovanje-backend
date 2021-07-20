package com.epsih.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AUTHORITY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

   @Id
   @Column(name = "NAME", length = 50)
   @NotNull
   private String name;

}
