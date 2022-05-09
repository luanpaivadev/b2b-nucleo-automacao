package com.accenture.b2bautomacaostc.core.queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilaConsultasSTCConfig {

    public static final String QUEUE_NAME = "consultasSTC";
    public static final String EXCHANGE_NAME = "consultas";
    public static final String ROUTING_KEY = "consultas.stc";

    @Bean
    DirectExchange directExchangeAutomacaoSTC() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queueAutomacaoSTC() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    Binding bindingAutomacaoSTC() {
        return BindingBuilder.bind(queueAutomacaoSTC()).to(directExchangeAutomacaoSTC()).with(ROUTING_KEY);
    }

}
