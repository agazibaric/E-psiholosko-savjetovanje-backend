package com.epsih.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reset_password_token")
@Builder
public class ResetPasswordToken {

   public static final Integer DAYS_TO_EXPIRE = 1;

   @Id
   @GeneratedValue(strategy = IDENTITY)
   private Long id;
   @Column(unique = true)
   private String token;
   @OneToOne(fetch = LAZY)
   private User user;
   private Instant expiryDate;

}
