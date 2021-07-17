package com.epsih.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WebSocketException extends RuntimeException {

   public WebSocketException(String message) {
      super(message);
   }

}
