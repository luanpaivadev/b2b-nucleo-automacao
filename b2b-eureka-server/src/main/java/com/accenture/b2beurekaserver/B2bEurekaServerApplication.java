package com.accenture.b2beurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class B2bEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(B2bEurekaServerApplication.class, args);
	}

}
