package com.epsih.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHORITY")
public class Authority {

   @Id
   @Column(name = "NAME", length = 50)
   @NotNull
   private String name;

}
