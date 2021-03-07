package com.epsih.rest;

import com.epsih.dto.RegisterDto;
import com.epsih.model.User;
import com.epsih.service.AuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.epsih.service.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserRestController {

   private final UserService userService;

   @GetMapping("/user")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }



 }
