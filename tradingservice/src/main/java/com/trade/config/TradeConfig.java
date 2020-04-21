package com.trade.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;
import com.trade.repo.TradeRepository;
import com.trade.service.TradeService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class TradeConfig {

  @Bean
  public TradeService tradeService(TradeRepository tradeRepository) {
    return new TradeService(tradeRepository);
  }

  @Bean
  public Docket swaggerConfiguration(){
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .paths(PathSelectors.ant("/trade/*"))
        .apis(RequestHandlerSelectors.basePackage("com.trade"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Trade Service", 
        "Api to provide ticker unit price in real time", "1.0",
        "Free to use",
        new springfox.documentation.service.Contact("Biswarup", "http://tradingservice.api.io",
            "biswarup.banerjee@trade.com"),
        "Api License", 
        "http://tradingLicense.api.io", 
        Collections.emptyList());
  }

}
