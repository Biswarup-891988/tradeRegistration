package com.trade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.trade.repo.TradeRepository;
import com.trade.service.TradeService;

@Configuration
public class TradeConfig {

  @Bean
  public TradeService tradeService(TradeRepository tradeRepository) {
    return new TradeService(tradeRepository);
  }

}
