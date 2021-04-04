package com.epsih.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInputDto {

   @NotBlank
   private String to;
   @NotBlank
   private String content;
   @NotNull
   private Long meetingId;

}
