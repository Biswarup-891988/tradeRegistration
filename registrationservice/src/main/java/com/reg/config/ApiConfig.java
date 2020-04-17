package com.reg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.reg.repo.UserRepository;
import com.reg.service.RegistrationService;

@Configuration
public class ApiConfig {

  @Bean
  public RegistrationService resgistrationService(UserRepository repo) {
    return new RegistrationService(repo);
  }

}
