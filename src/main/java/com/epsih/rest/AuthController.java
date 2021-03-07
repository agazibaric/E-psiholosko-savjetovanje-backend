package com.epsih.rest;

import com.epsih.dto.LoginDto;
import com.epsih.dto.RegisterDto;
import com.epsih.security.jwt.JWTToken;
import com.epsih.service.AuthService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.epsih.security.jwt.JWTFilter;
import com.epsih.security.jwt.TokenProvider;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

/**
 * Controller to authenticate users.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

   private final AuthService authService;

   @PostMapping("/authenticate")
   public ResponseEntity<JWTToken> authenticate(@Valid @RequestBody LoginDto loginDto) {
      String jwt = authService.authenticate(loginDto);

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

      return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
   }

   @PostMapping("/register")
   public ResponseEntity register(@Valid @RequestBody RegisterDto registerDto) {
      authService.register(registerDto);
      return ResponseEntity.ok().build();
   }

   @GetMapping("activate/{token}")
   public ResponseEntity<String> verifyAccount(@PathVariable String token) {
      authService.activateAccount(token);
      return new ResponseEntity<>("Account activated successfully", OK);
   }

}
