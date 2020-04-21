package com.reg.service;

import java.util.Random;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.reg.exception.TradeException;
import com.reg.model.TradeInfo;
import reactor.core.publisher.Mono;

public class TradeService {

  private final WebClient.Builder webClient;

  public TradeService(Builder webClient) {
    super();
    this.webClient = webClient;
  }

  @HystrixCommand(fallbackMethod = "getFallBackTickerDetails",
      commandProperties = {
          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")},
      ignoreExceptions = {TradeException.class})
  public TradeInfo getTickerDetails(String ticker) {
    TradeInfo trade = webClient.build().get()
        .uri("http://trade-service/tradingService/trade/getTradeDetails/" + ticker).retrieve()
        .onStatus(HttpStatus::is4xxClientError,
            clientResponse -> Mono.error(new TradeException("Ticker Not Found")))
        .bodyToMono(TradeInfo.class).block();
    return trade;
  }

  public TradeInfo getFallBackTickerDetails(String ticker) {
    TradeInfo trade = new TradeInfo();
    Random ran = new Random();
    trade.setId(10 * ran.nextInt());
    trade.setTicker(ticker);
    trade.setUnitPrice(0);
    return trade;
  }

}
