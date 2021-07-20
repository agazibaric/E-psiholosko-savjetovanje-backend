package com.epsih.model.meeting;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.epsih.enums.MessageType;
import com.epsih.model.meeting.Meeting;
import com.epsih.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_message")
	private Long id;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime creationDate;

	@NotNull
   @NotBlank
   @Column(name = "content")
	private String content;

   @Column(name = "message_type")
   @Enumerated(EnumType.STRING)
   private MessageType messageType;

   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
   @JoinColumn(name="fk_sender")
	private User sender;

   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
   @JoinColumn(name="fk_recipient")
	private User recipient;

   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnore
   @JoinColumn(name="fk_meeting")
   private Meeting meeting;

}
