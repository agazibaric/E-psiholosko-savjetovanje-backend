package com.epsih.rest;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.dto.LoginDto;
import com.epsih.dto.RegisterDto;
import com.epsih.security.jwt.JWTFilter;
import com.epsih.security.jwt.JWTToken;
import com.epsih.service.AuthService;

import lombok.AllArgsConstructor;

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
