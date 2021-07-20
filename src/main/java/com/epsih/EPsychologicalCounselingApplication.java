package com.epsih;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "E-Psychological Counseling API"))
public class EPsychologicalCounselingApplication {

   public static void main(String[] args) {
      SpringApplication.run(EPsychologicalCounselingApplication.class, args);
   }

}
