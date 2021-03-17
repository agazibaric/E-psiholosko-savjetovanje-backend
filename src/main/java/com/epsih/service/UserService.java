package com.epsih.service;

import com.epsih.exceptions.NotFoundException;
import com.epsih.security.SecurityUtils;
import com.epsih.model.User;
import com.epsih.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
   public User getById(Long id) {
      return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
   }

}