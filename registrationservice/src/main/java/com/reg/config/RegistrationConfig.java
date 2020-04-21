package com.reg.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import com.reg.repo.UserPortfolioRepository;
import com.reg.repo.UserRepository;
import com.reg.service.RegistrationService;
import com.reg.service.TradeService;

@Configuration
public class RegistrationConfig {

  @Bean
  public RegistrationService resgistrationService(UserRepository repo,
      UserPortfolioRepository userPortfolioRepository, TradeService tradeService) {
    return new RegistrationService(repo,userPortfolioRepository,tradeService);
  }
  
  @Bean
  public TradeService tradeService(WebClient.Builder webClient) {
    return new TradeService(webClient);
  }

  
  @Bean
  @LoadBalanced
  public WebClient.Builder webClient() {
    return WebClient.builder();
  }

}
