package com.epsih.service;

import com.epsih.dto.MessageInputDto;
import com.epsih.dto.MessageOutputDto;
import com.epsih.enums.MessageType;
import com.epsih.exceptions.NotFoundException;
import com.epsih.exceptions.UnauthorizedException;
import com.epsih.model.Meeting;
import com.epsih.model.Message;
import com.epsih.repository.MeetingRepository;
import com.epsih.repository.MessageRepository;
import com.epsih.repository.UserRepository;
import com.epsih.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

   private static final Logger LOG = LoggerFactory.getLogger(ChatService.class);
   private final SimpMessagingTemplate simpMessagingTemplate;
   private final MessageRepository messageRepository;
   private final UserRepository userRepository;
   private final MeetingRepository meetingRepository;

   public void sendMessage(String username, MessageInputDto messageInputDto) {
      LOG.info("Received message from user: " + username);
      LOG.info("Message: " + messageInputDto);

      Message message = Message.builder()
         .sender(userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Sender not found")))
         .recipient(userRepository.findByUsername(messageInputDto.getTo()).orElseThrow(() -> new NotFoundException("Recipient not found")))
         .meeting(meetingRepository.findById(messageInputDto.getMeetingId()).orElseThrow(() -> new NotFoundException("Meeting not found")))
         .content(messageInputDto.getContent())
         .creationDate(LocalDateTime.now())
         .messageType(MessageType.TEXT)
         .build();

      messageRepository.save(message);
      simpMessagingTemplate.convertAndSendToUser(messageInputDto.getTo(), "/queue/chat", message);
   }

   @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
   public List<MessageOutputDto> getAllMyMessages(Long meetingId) {
      Meeting meeting = meetingRepository.findById(meetingId)
         .orElseThrow(() -> new NotFoundException("Meeting not found"));
      return meeting.getMessages().stream()
         .map(msg -> MessageOutputDto.builder()
            .content(msg.getContent())
            .creationDate(msg.getCreationDate())
            .type(msg.getMessageType().toString().toLowerCase())
            .meetingId(meetingId)
            .self(SecurityUtils.getCurrentUsername()
               .orElseThrow(() -> new UnauthorizedException("Unauthorized to view resource"))
               .equals(msg.getSender().getUsername()))
            .build())
         .collect(Collectors.toList());
   }

}
