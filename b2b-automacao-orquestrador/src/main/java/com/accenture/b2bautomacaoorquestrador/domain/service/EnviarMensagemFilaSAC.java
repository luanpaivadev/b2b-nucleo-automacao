package com.accenture.b2bautomacaoorquestrador.domain.service;

import com.accenture.b2bautomacaoorquestrador.core.queue.FilaConsultasSACConfig;
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
public class EnviarMensagemFilaSAC implements EnviarMensagem {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void send(DadosChecklist dadosChecklist) {
        var listaConsultasSAC = dadosChecklist.getConsultas().stream()
                .filter(consulta -> consulta.getSistema().equals("SAC"))
                .collect(Collectors.toList());

        if (!listaConsultasSAC.isEmpty()) {
            var enviarParaAutomacaoSAC = new DadosChecklist();
            modelMapper.map(dadosChecklist, enviarParaAutomacaoSAC);
            enviarParaAutomacaoSAC.setConsultas(listaConsultasSAC);

            amqpTemplate.convertAndSend(
                    FilaConsultasSACConfig.EXCHANGE_NAME,
                    FilaConsultasSACConfig.ROUTING_KEY,
                    enviarParaAutomacaoSAC
            );
        }
    }

    @Override
    public void send(List<DadosChecklist> dadosChecklists) {

        for (DadosChecklist dadosChecklist : dadosChecklists) {

            List<Consulta> listaConsultasSAC = dadosChecklist.getConsultas().stream()
                    .filter(consulta -> consulta.getSistema().equals("SAC"))
                    .collect(Collectors.toList());

            if (!listaConsultasSAC.isEmpty()) {
                var enviarParaAutomacaoSAC = new DadosChecklist();
                modelMapper.map(dadosChecklist, enviarParaAutomacaoSAC);
                enviarParaAutomacaoSAC.setConsultas(listaConsultasSAC);

                amqpTemplate.convertAndSend(
                        FilaConsultasSACConfig.EXCHANGE_NAME,
                        FilaConsultasSACConfig.ROUTING_KEY,
                        enviarParaAutomacaoSAC
                );
            }
        }
    }

}
