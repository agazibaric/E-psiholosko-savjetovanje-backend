package com.epsih.dto;

import com.epsih.enums.TerminType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TerminDto {

   @NotNull
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime terminStart;

   @NotNull
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime terminEnd;

   private String description;

   @NotNull
   private TerminType terminType;

   @NotNull
   private Long meetingId;

}
