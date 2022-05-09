package com.accenture.b2bautomacaostc.domain.service;

import com.accenture.b2bautomacaostc.api.v1.assembler.ConsultaModelMapper;
import com.accenture.b2bautomacaostc.api.v1.dto.ConsultaDTO;
import com.accenture.b2bautomacaostc.domain.exceptions.ParametrosInvalidosExceptions;
import com.accenture.b2bautomacaostc.domain.model.Consulta;
import com.accenture.b2bautomacaostc.domain.model.Status;
import com.accenture.b2bautomacaostc.domain.model.StatusProcessamento;
import com.accenture.b2bautomacaostc.domain.repository.ConsultasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

@Slf4j
@Service
public class ConsultasService implements ConsultasAutomacaoSTC {

    @Autowired
    private ConsultasRepository consultasRepository;

    @Autowired
    private ConsultaModelMapper consultaModelMapper;

    @Transactional
    public void save(Consulta consulta) {
        consultasRepository.save(consulta);
    }

    @Override
    public ConsultaDTO consultaDDR(Consulta consulta) {

        var circuito = consulta.getDadosChecklist().getCircuito();
        var terminal = consulta.getDadosChecklist().getTerminal();

        try {
            if (!StringUtils.hasLength(circuito) && StringUtils.hasLength(terminal)) {
                // OBTEM CIRCUITO
                consulta.setStatus(Status.SUCESSO);
                consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
                circuito = "123456789";
                consulta.getDadosChecklist().setCircuito(circuito);
                consulta.setDadosSaida(MessageFormat.format("Circuito: {0}", circuito));
                log.info("Consulta de [DDR] executada com sucesso.");
            } else {
                // OBTEM TERMINAL
                consulta.setStatus(Status.SUCESSO);
                consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
                terminal = "987654321";
                consulta.getDadosChecklist().setTerminal(terminal);
                consulta.setDadosSaida(MessageFormat.format("Terminal: {0}", terminal));
                log.info("Consulta de [DDR] executada com sucesso.");
            }
        } catch (Exception e) {
            var mensagemErro = MessageFormat.format("Erro na consulta de DDR: {0}", e.getMessage());
            consulta.setStatus(Status.ERRO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setMensagemStatus(mensagemErro);
            log.error(mensagemErro);
        } finally {
            consultasRepository.save(consulta);
        }
        return consultaModelMapper.toDTOObject(consulta);
    }

    @Override
    public ConsultaDTO consultaReparo(Consulta consulta) {
        try {
            consulta.setStatus(Status.SUCESSO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setDadosSaida("Não há reparo aberto");
            log.info("Consulta de [Reparo] executada com sucesso.");
        } catch (Exception e) {
            var mensagemErro = MessageFormat.format("Erro na consulta de Reparo: {0}", e.getMessage());
            consulta.setStatus(Status.ERRO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setMensagemStatus(mensagemErro);
            log.error(mensagemErro);
        } finally {
            consultasRepository.save(consulta);
        }
        return consultaModelMapper.toDTOObject(consulta);
    }

    @Override
    public ConsultaDTO consultaBloqueio(Consulta consulta) {
        try {
            var circuito = consulta.getDadosChecklist().getCircuito();
            if (!StringUtils.hasLength(circuito)) {
                throw new ParametrosInvalidosExceptions("Motivo: Circuito nulo.");
            }
            consulta.getDadosChecklist().setCircuito(circuito);
            consulta.setStatus(Status.SUCESSO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setDadosSaida("Não há bloqueio");
            log.info("Consulta de [Bloqueio] executada com sucesso.");
        } catch (Exception e) {
            var mensagemErro = MessageFormat.format("Erro na consulta de Bloqueio: {0}", e.getMessage());
            consulta.setStatus(Status.ERRO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setMensagemStatus(mensagemErro);
            log.error(mensagemErro);
        } finally {
            consultasRepository.save(consulta);
        }
        return consultaModelMapper.toDTOObject(consulta);
    }

    @Override
    public ConsultaDTO consultaOs(Consulta consulta) {
        try {
            consulta.setStatus(Status.SUCESSO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setDadosSaida("Tem Os Aberta");
            log.info("Consulta de [Os] executada com sucesso.");
        } catch (Exception e) {
            var mensagemErro = MessageFormat.format("Erro na consulta de Os: {0}", e.getMessage());
            consulta.setStatus(Status.ERRO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setMensagemStatus(mensagemErro);
            log.error(mensagemErro);
        } finally {
            consultasRepository.save(consulta);
        }
        return consultaModelMapper.toDTOObject(consulta);
    }

    @Override
    public ConsultaDTO consultaOsDespachada(Consulta consulta) {
        try {
            consulta.setStatus(Status.SUCESSO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setDadosSaida("Tem Os Despachada");
            log.error("Consulta de [Os Despachada] executada com sucesso.");
        } catch (Exception e) {
            var mensagemErro = MessageFormat.format("Erro na consulta de Os Despachada: {0}", e.getMessage());
            consulta.setStatus(Status.ERRO);
            consulta.setStatusProcessamento(StatusProcessamento.PROCESSADO);
            consulta.setMensagemStatus(mensagemErro);
            log.error(mensagemErro);
        } finally {
            consultasRepository.save(consulta);
        }
        return consultaModelMapper.toDTOObject(consulta);
    }

}
