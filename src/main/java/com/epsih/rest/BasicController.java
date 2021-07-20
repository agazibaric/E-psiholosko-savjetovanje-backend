package com.epsih.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

   @GetMapping("/")
   public String health() {
      return "E-Psychological Counseling is working!";
   }
}
