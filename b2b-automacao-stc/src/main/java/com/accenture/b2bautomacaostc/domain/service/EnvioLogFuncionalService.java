package com.accenture.b2bautomacaostc.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnvioLogFuncionalService {

    private EnvioLogFuncionalService() {
    }

    public void enviarLogFuncional() {
        log.info("Enviando LogFuncional...");
    }
}
