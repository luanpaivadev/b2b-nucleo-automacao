package com.accenture.b2bautomacaosac.domain.service;

import com.accenture.b2bautomacaosac.api.v1.dto.DadosSaidaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
public class EnvioCallbackService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean enviarCallback(DadosSaidaDTO dadosSaidaDTO) {
        try {
            final var urlCallback = dadosSaidaDTO.getDadosChecklist().getUrlCallback();
            log.info("Enviando callback. URL: {}", urlCallback);
            var response = restTemplate.postForEntity(new URI(urlCallback), dadosSaidaDTO, String.class);
            if (response.getStatusCode() == HttpStatus.CREATED) {
                log.info("Callback enviado com sucesso!");
                log.info(response.getBody());
                return true;
            } else {
                log.error("Erro ao enviar callback.");
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
