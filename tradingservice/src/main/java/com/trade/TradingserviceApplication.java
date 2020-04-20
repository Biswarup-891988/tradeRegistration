package com.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TradingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingserviceApplication.class, args);
	}

}
