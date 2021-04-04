package com.epsih.websocket;

import com.epsih.dto.MessageInputDto;
import com.epsih.exceptions.WebSocketException;
import com.epsih.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.support.MissingSessionUserException;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class WebSocketController {

   private final ChatService chatService;

   @MessageMapping("/message")
   public void sendSpecific(@Payload MessageInputDto messageInputDto, @Header("simpSessionId") String sessionId, Principal user) {
      chatService.sendMessage(user.getName(), messageInputDto);
   }

   @MessageExceptionHandler
   public WebSocketException handleException(MissingSessionUserException e) {
      return new WebSocketException(e.getMessage());
   }

}
