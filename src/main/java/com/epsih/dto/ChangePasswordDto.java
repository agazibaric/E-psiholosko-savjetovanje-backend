package com.epsih.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

   @NotNull
   @Size(min = 4, max = 100)
   private String oldPassword;

   @NotNull
   @Size(min = 4, max = 100)
   private String newPassword;

   @NotNull
   @Size(min = 4, max = 100)
   private String repeatPassword;

}
