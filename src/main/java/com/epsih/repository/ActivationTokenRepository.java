package com.epsih.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epsih.model.ActivationToken;

@Repository
public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
   Optional<ActivationToken> findByToken(String token);
}
