package com.accenture.b2bautomacaoorquestrador.domain.service;

import com.accenture.b2bautomacaoorquestrador.core.queue.FilaConsultasSTCConfig;
import com.accenture.b2bautomacaoorquestrador.domain.model.Consulta;
import com.accenture.b2bautomacaoorquestrador.domain.model.DadosChecklist;
import com.accenture.b2bautomacaoorquestrador.domain.repository.EnviarMensagem;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EnviarMensagemFilaSTC implements EnviarMensagem {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void send(DadosChecklist dadosChecklist) {

        List<Consulta> listaConsultasSTC = dadosChecklist.getConsultas().stream()
                .filter(consulta -> consulta.getSistema().equals("STC"))
                .collect(Collectors.toList());

        if (!listaConsultasSTC.isEmpty()) {
            var enviarParaAutomacaoSTC = new DadosChecklist();
            modelMapper.map(dadosChecklist, enviarParaAutomacaoSTC);
            enviarParaAutomacaoSTC.setConsultas(listaConsultasSTC);

            amqpTemplate.convertAndSend(
                    FilaConsultasSTCConfig.EXCHANGE_NAME,
                    FilaConsultasSTCConfig.ROUTING_KEY,
                    enviarParaAutomacaoSTC
            );
        }
    }

    @Override
    public void send(List<DadosChecklist> dadosChecklists) {

        for (DadosChecklist dadosChecklist : dadosChecklists) {

            List<Consulta> listaConsultasSTC = dadosChecklist.getConsultas().stream()
                    .filter(consulta -> consulta.getSistema().equals("STC"))
                    .collect(Collectors.toList());

            if (!listaConsultasSTC.isEmpty()) {
                var enviarParaAutomacaoSTC = new DadosChecklist();
                modelMapper.map(dadosChecklist, enviarParaAutomacaoSTC);
                enviarParaAutomacaoSTC.setConsultas(listaConsultasSTC);

                amqpTemplate.convertAndSend(
                        FilaConsultasSTCConfig.EXCHANGE_NAME,
                        FilaConsultasSTCConfig.ROUTING_KEY,
                        enviarParaAutomacaoSTC
                );
            }
        }
    }

}
