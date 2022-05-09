package com.accenture.urlcallback.controller;

import com.accenture.urlcallback.dto.DadosSaidaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping("/v1/callback")
public class CallbackController {

    @PostMapping
    public ResponseEntity<String> getCallback(@RequestBody DadosSaidaDTO dadosSaidaDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dadosSaidaDTO);
        log.info("Objeto recebido: {}", json);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MessageFormat.format("Request recebido com sucesso: {0}", json));
    }
}
