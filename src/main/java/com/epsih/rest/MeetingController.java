package com.epsih.rest;

import com.epsih.dto.MessageOutputDto;
import com.epsih.model.Message;
import com.epsih.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/meeting")
public class MeetingController {

   private final ChatService chatService;

   @GetMapping("/{meetingId}/messages")
   public List<MessageOutputDto> getAllMyMessagesForMeeting(@PathVariable("meetingId") Long meetingId) {
      return chatService.getAllMyMessages(meetingId);
   }

}
