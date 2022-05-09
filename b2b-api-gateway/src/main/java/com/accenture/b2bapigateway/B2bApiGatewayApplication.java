package com.accenture.b2bapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class B2bApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(B2bApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("b2b-automacao-orquestrador", r -> r.path("/v1/checklist/consultas/**")
						.uri("lb://b2b-automacao-orquestrador"))
				.route("b2b-automacao-sac", r -> r.path("/v1/checklist/sac/consultas/**")
						.uri("lb://b2b-automacao-sac"))
				.route("b2b-automacao-stc", r -> r.path("/v1/checklist/stc/consultas/**")
						.uri("lb://b2b-automacao-stc"))
				.build();
	}

}
