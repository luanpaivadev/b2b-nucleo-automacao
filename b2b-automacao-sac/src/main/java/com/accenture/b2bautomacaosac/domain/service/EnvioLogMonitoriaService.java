package com.accenture.b2bautomacaosac.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnvioLogMonitoriaService {

    public void enviarLogMonitoria() {
        log.info("Enviando LogMonitoria...");
    }
}
