package com.accenture.b2bautomacaosac.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnvioLogFuncionalService {

    public void enviarLogFuncional() {
        log.info("Enviando LogFuncional...");
    }
}
