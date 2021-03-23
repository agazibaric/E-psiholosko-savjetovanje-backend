package com.epsih.rest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {

   @ExceptionHandler(value = Exception.class)
   public ResponseEntity<?> handleException(Exception e) {
      if (e instanceof DataIntegrityViolationException) {
         //DataIntegrityViolationException ex = (DataIntegrityViolationException) e;
         return new ResponseEntity<String>("SQL constraint error", HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
   }
}
