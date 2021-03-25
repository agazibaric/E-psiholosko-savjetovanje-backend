package com.epsih.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epsih.exceptions.NotFoundException;
import com.epsih.model.Meeting;
import com.epsih.model.User;
import com.epsih.repository.MeetingRepository;
import com.epsih.repository.UserRepository;
import com.epsih.security.SecurityUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

   private final UserRepository userRepository;

   @Transactional(readOnly = true)
   public Optional<User> getUserWithAuthorities() {
	   return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
   }

   @Transactional(readOnly = true)
   public List<User> getUsers() {
	   return userRepository.findAll();
   }

   @Transactional(readOnly = true)
   public User getById(Long id) {
      return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
   }

   public void deleteUser(Long id) {
	   userRepository.deleteById(id);
   }

   public void saveUser(User user) {
	   userRepository.save(user);
   }

}
