package com.accenture.b2bautomacaosac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class B2bAutomacaoSacApplication {

	public static void main(String[] args) {
		SpringApplication.run(B2bAutomacaoSacApplication.class, args);
	}

}
