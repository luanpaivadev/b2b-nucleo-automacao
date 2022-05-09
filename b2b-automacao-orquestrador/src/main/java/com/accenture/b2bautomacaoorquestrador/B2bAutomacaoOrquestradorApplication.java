package com.accenture.b2bautomacaoorquestrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class B2bAutomacaoOrquestradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(B2bAutomacaoOrquestradorApplication.class, args);
	}

}
