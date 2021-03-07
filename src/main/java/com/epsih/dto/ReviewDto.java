package com.epsih.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReviewDto {

   @NotNull
   private Long targetId;

   @NotNull
   @Min(1)
   @Max(5)
   private Integer grade;

   private String description;

}
