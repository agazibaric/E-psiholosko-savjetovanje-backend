package com.epsih.service;

import com.epsih.constants.AuthorityConstants;
import com.epsih.dto.LoginDto;
import com.epsih.dto.RegisterDto;
import com.epsih.exceptions.BadRequestException;
import com.epsih.exceptions.UserException;
import com.epsih.model.ActivationToken;
import com.epsih.model.User;
import com.epsih.repository.ActivationTokenRepository;
import com.epsih.repository.AuthorityRepository;
import com.epsih.repository.UserRepository;
import com.epsih.security.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

   private final TokenProvider tokenProvider;
   private final AuthenticationManagerBuilder authenticationManagerBuilder;
   private final ActivationTokenRepository activationTokenRepository;
   private final UserRepository userRepository;
   private final ModelMapper modelMapper;
   private final PasswordEncoder passwordEncoder;
   private final AuthorityRepository authorityRepository;

   public String authenticate(LoginDto loginDto) {
      UsernamePasswordAuthenticationToken authenticationToken =
         new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      boolean rememberMe = (loginDto.getRememberMe() == null) ? false : loginDto.getRememberMe();
      return tokenProvider.createToken(authentication, rememberMe);
   }

   public void activateAccount(String token) {
      Optional<ActivationToken> activationToken = activationTokenRepository.findByToken(token);
      fetchUserAndEnable(activationToken.orElseThrow(() -> new BadRequestException("Invalid Activation Token")));
   }

   @Transactional
   public void register(RegisterDto registerDto) {
      if (userRepository.findByUsername(registerDto.getUsername()).isPresent())
         throw new UserException("User with given username already exists");

      User user = modelMapper.map(registerDto, User.class);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setAuthorities(new HashSet<>(Arrays.asList(authorityRepository.getOne(AuthorityConstants.ROLE_USER))));
      user.setActivated(false);

      userRepository.save(user);

      String token = generateActivationToken(user);
      // TODO: send activation mail with token
   }

   private void fetchUserAndEnable(ActivationToken activationToken) {
      String username = activationToken.getUser().getUsername();
      User user = userRepository.findByUsername(username).orElseThrow(() -> new UserException("User not found with name - " + username));
      user.setActivated(true);
      userRepository.save(user);
   }

   private String generateActivationToken(User user) {
      String token = UUID.randomUUID().toString();
      ActivationToken activationToken = new ActivationToken();
      activationToken.setToken(token);
      activationToken.setUser(user);
      activationTokenRepository.save(activationToken);
      return token;
   }

}
