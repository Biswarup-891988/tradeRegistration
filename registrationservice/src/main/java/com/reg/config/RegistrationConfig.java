package com.reg.config;

import java.util.Collections;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import com.reg.repo.UserPortfolioRepository;
import com.reg.repo.UserRepository;
import com.reg.service.RegistrationService;
import com.reg.service.TradeService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
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
  
  @Bean
  public Docket swaggerConfiguration(){
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.ant("/reg/*"))
        .apis(RequestHandlerSelectors.basePackage("com.reg"))
        .build()
        .apiInfo(apiInfo());
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Trade Service", 
        "Api to provide trading service", "1.0",
        "Free to use",
        new springfox.documentation.service.Contact("Biswarup", "http://tradingservice.api.io",
            "biswarup.banerjee@trade.com"),
        "Api License", 
        "http://tradingLicense.api.io", 
        Collections.emptyList());
  }

}
