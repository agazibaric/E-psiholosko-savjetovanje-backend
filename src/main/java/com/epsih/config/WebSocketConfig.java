package com.epsih.config;

import com.epsih.security.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

   protected static final String AUTHORIZATION_HEADER_STOMP = "X-Authorization";
   private final TokenProvider tokenProvider;

   @Override
   public void configureMessageBroker(MessageBrokerRegistry config) {
      // Broker endpoint (Subscribe endpoint)
      config.enableSimpleBroker("/queue");
      // Prefix for message mappings in web socket controller
      config.setApplicationDestinationPrefixes("/chat");
   }

   @Override
   public void registerStompEndpoints(StompEndpointRegistry registry) {
      // Connect endpoint
      registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
   }

   @Override
   public void configureClientInboundChannel(ChannelRegistration registration) {
      registration.interceptors(new ChannelInterceptor() {
         @Override
         public Message<?> preSend(Message<?> message, MessageChannel channel) {
            StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
            if (accessor == null)
               return message;
            StompCommand command = accessor.getCommand();
            if (command == null)
               return message;
            if (command.equals(StompCommand.CONNECT)) {
               String jwt = resolveToken(accessor);
               if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                  Authentication authentication = tokenProvider.getAuthentication(jwt);
                  accessor.setUser(authentication);
               }
            }
            return message;
         }
      });
   }

   private String resolveToken(StompHeaderAccessor accessor) {
      List<String> authorization = accessor.getNativeHeader(AUTHORIZATION_HEADER_STOMP);
      if (authorization == null || authorization.isEmpty())
         return null;
      String bearerToken = authorization.get(0);
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
         return bearerToken.substring(7);
      }
      return null;
   }

}
