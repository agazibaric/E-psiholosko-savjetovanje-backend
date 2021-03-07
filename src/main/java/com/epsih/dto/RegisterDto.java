package com.epsih.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

   @NotNull
   @Size(min = 1, max = 50)
   private String username;

   @NotNull
   @Size(min = 4, max = 100)
   private String password;

   @NotNull
   @Size(min = 4, max = 50)
   private String firstname;

   @NotNull
   @Size(min = 4, max = 50)
   private String lastname;

   @NotNull
   @Size(min = 4, max = 50)
   private String email;

   @NotNull
   private String phoneNumber;

}
