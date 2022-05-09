package com.accenture.b2bautomacaosac.core.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilaConsultasSACConfig {

    public static final String QUEUE_NAME = "consultasSAC";
    public static final String EXCHANGE_NAME = "consultas";
    public static final String ROUTING_KEY = "consultas.sac";

    @Bean
    DirectExchange directExchangeAutomacaoSAC() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queueAutomacaoSAC() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    Binding bindingAutomacaoSAC() {
        return BindingBuilder.bind(queueAutomacaoSAC()).to(directExchangeAutomacaoSAC()).with(ROUTING_KEY);
    }

}
