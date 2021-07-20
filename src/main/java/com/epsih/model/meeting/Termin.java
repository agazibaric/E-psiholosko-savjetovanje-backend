package com.epsih.model.meeting;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.epsih.enums.TerminType;
import com.epsih.model.meeting.Meeting;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "termin")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Termin {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime terminStart;

   @NotNull
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime terminEnd;

   private String description;

   @Column(name = "termin_type")
   private TerminType terminType;

   @ManyToOne
   @JoinColumn(name = "fk_meeting")
   @JsonIgnore
   private Meeting meeting;

}
