package com.reg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import com.reg.repo.UserPortfolioRepository;
import com.reg.repo.UserRepository;
import com.reg.service.RegistrationService;

@Configuration
public class RegistrationConfig {

  @Bean
  public RegistrationService resgistrationService(UserRepository repo, WebClient.Builder webClient,
      UserPortfolioRepository userPortfolioRepository) {
    return new RegistrationService(repo, webClient, userPortfolioRepository);
  }

  @Bean
  public WebClient.Builder webClient() {
    return WebClient.builder();
  }

}
