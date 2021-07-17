package com.epsih.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageOutputDto {

   private String content;
   private boolean self;
   private String type;
   private LocalDateTime creationDate;
   private Long meetingId;
   
}
