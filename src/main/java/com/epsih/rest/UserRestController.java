package com.epsih.rest;

import com.epsih.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.epsih.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserRestController {

   private final UserService userService;

   @GetMapping("/users/me")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }



 }
