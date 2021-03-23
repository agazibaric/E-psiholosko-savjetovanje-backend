package com.epsih.rest;

import java.util.List;
import java.util.Set;

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
@RequestMapping("/api/user")
public class UserRestController {

   private final UserService userService;

   @GetMapping("/me")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }
   
   @GetMapping("/me/meetings")
   public ResponseEntity<List<Meeting>> getMyMeetings() {
	   return new ResponseEntity<>(userService.getMyMeetings(), HttpStatus.OK);
   }
   
   @GetMapping
   public ResponseEntity<List<User>> getUsers() {
      return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
	   User user = userService.getById(id);
	   return new ResponseEntity<>(user, HttpStatus.OK);
   }
   
   @GetMapping("/{id}/meetings")
   public ResponseEntity<List<Meeting>> getUserMeetingById(@PathVariable Long id) {
	   List<Meeting> meetings = userService.getMeetings(id);
	   return new ResponseEntity<>(meetings, HttpStatus.OK);
   }
   
   @PostMapping
   public ResponseEntity<Void> registerUser(@RequestBody User user) {
	   userService.saveUser(user);
	   return new ResponseEntity<>(HttpStatus.OK);
   }
   
   @PutMapping("/{id}")
   public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable Long id) {
	   user.setId(id);
	   userService.saveUser(user);
	   return new ResponseEntity<>(HttpStatus.OK);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<User> deleteUser(@PathVariable Long id) {
	   userService.deleteUser(id);
	   return new ResponseEntity<>(HttpStatus.OK);
   }



 }
