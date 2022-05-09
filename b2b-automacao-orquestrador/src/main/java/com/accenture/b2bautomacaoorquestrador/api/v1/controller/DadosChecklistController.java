package com.accenture.b2bautomacaoorquestrador.api.v1.controller;

import com.accenture.b2bautomacaoorquestrador.api.v1.assembler.DadosChecklistModelMapper;
import com.accenture.b2bautomacaoorquestrador.api.v1.model.dto.Response;
import com.accenture.b2bautomacaoorquestrador.api.v1.model.input.DadosChecklistInput;
import com.accenture.b2bautomacaoorquestrador.domain.exceptions.ParametrosInvalidosExceptions;
import com.accenture.b2bautomacaoorquestrador.domain.model.DadosChecklist;
import com.accenture.b2bautomacaoorquestrador.domain.model.Status;
import com.accenture.b2bautomacaoorquestrador.domain.service.DadosChecklistService;
import com.accenture.b2bautomacaoorquestrador.domain.service.EnviarMensagemFilaSAC;
import com.accenture.b2bautomacaoorquestrador.domain.service.EnviarMensagemFilaSTC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/checklist/consultas")
public class DadosChecklistController {

    @Autowired
    private DadosChecklistService dadosChecklistService;

    @Autowired
    private DadosChecklistModelMapper dadosChecklistModelMapper;

    @Autowired
    private EnviarMensagemFilaSAC enviarMensagemFilaSAC;

    @Autowired
    private EnviarMensagemFilaSTC enviarMensagemFilaSTC;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid DadosChecklistInput dadosChecklistInput) {
        Response response = new Response();
        try {
            DadosChecklist dadosChecklist = dadosChecklistModelMapper.toDomainObject(dadosChecklistInput);
            dadosChecklistService.save(dadosChecklist);
            response.setRequestId(dadosChecklist.getRequestId());
            response.setStatus(Status.SUCESSO);

            //ENVIA AS CONSULTAS PARA A FILA DAS AUTOMAÇÕES
            enviarMensagemFilaSAC.send(dadosChecklist);
            enviarMensagemFilaSTC.send(dadosChecklist);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (ParametrosInvalidosExceptions e) {
            response.setStatus(Status.ERRO);
            response.setMensagem(MessageFormat.format("Erro: {0}", e.getMessage()));
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll(@RequestBody List<DadosChecklistInput> listDadosChecklistInput) {
        Response response = new Response();
        try {
            List<DadosChecklist> dadosChecklists = dadosChecklistModelMapper.toDomainListObject(listDadosChecklistInput);
            String loteId = UUID.randomUUID().toString();
            dadosChecklistService.saveAll(dadosChecklists, loteId);
            response.setStatus(Status.SUCESSO);
            response.setLoteId(loteId);

            //ENVIA AS CONSULTAS PARA A FILA DAS AUTOMAÇÕES
            enviarMensagemFilaSAC.send(dadosChecklists);
            enviarMensagemFilaSTC.send(dadosChecklists);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ParametrosInvalidosExceptions e) {
            response.setStatus(Status.ERRO);
            response.setMensagem(MessageFormat.format("Erro: {0}", e.getMessage()));
            return ResponseEntity.badRequest().body(response);
        }
    }

}
