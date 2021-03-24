package com.epsih.rest;

import java.util.List;
import java.util.Set;

import com.epsih.constants.Endpoints;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epsih.model.Doctor;
import com.epsih.model.Meeting;
import com.epsih.model.Patient;
import com.epsih.model.User;
import com.epsih.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(Endpoints.USER_ROOT)
public class UserController {

   private final UserService userService;

   @GetMapping(Endpoints.USER_ME)
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }

   @GetMapping
   public ResponseEntity<List<User>> getUsers() {
      return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
   }

   @GetMapping(Endpoints.USER_ID)
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
	   User user = userService.getById(id);
	   return new ResponseEntity<>(user, HttpStatus.OK);
   }

   @DeleteMapping(Endpoints.USER_ID)
   public ResponseEntity<User> deleteUser(@PathVariable Long id) {
	   userService.deleteUser(id);
	   return new ResponseEntity<>(HttpStatus.OK);
   }

 }
