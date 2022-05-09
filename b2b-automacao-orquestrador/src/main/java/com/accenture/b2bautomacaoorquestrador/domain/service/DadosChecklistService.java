package com.accenture.b2bautomacaoorquestrador.domain.service;

import com.accenture.b2bautomacaoorquestrador.domain.exceptions.ParametrosInvalidosExceptions;
import com.accenture.b2bautomacaoorquestrador.domain.model.DadosChecklist;
import com.accenture.b2bautomacaoorquestrador.domain.repository.DadosChecklistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class DadosChecklistService {

    @Autowired
    private DadosChecklistRepository dadosChecklistRepository;

    @Transactional
    public DadosChecklist save(DadosChecklist dadosChecklist) throws ParametrosInvalidosExceptions {

        var terminal = dadosChecklist.getTerminal();
        var circuito = dadosChecklist.getCircuito();
        var cliente = dadosChecklist.getCliente();
        var urlCallback = dadosChecklist.getUrlCallback();

        if (!StringUtils.hasLength(terminal) && !StringUtils.hasLength(circuito)) {
            throw new ParametrosInvalidosExceptions("Parâmetros inválidos. Motivo: Terminal e circuito nulo.");
        }
        if (!StringUtils.hasLength(cliente) || !StringUtils.hasLength(urlCallback)) {
            throw new ParametrosInvalidosExceptions("Parâmetros inválidos. Motivo: cliente e urlCallback nulo.");
        }

        dadosChecklist.setRequestId(UUID.randomUUID().toString());
        return dadosChecklistRepository.save(dadosChecklist);
    }

    @Transactional
    public List<DadosChecklist> saveAll(List<DadosChecklist> listDadosChecklist, String loteId)
            throws ParametrosInvalidosExceptions {

        for (DadosChecklist dadosChecklist : listDadosChecklist) {

            var terminal = dadosChecklist.getTerminal();
            var circuito = dadosChecklist.getCircuito();
            var urlCallback = dadosChecklist.getUrlCallback();

            if (!StringUtils.hasLength(terminal) && !StringUtils.hasLength(circuito)) {
                throw new ParametrosInvalidosExceptions("Parâmetros inválidos. Motivo: Terminal e circuito nulos.");
            }
            if (!StringUtils.hasLength(urlCallback)) {
                throw new ParametrosInvalidosExceptions("Parâmetros inválidos. Motivo: urlCallback nulo.");
            }
        }

        listDadosChecklist.stream().forEach(dadosChecklist -> {
            dadosChecklist.setRequestId(UUID.randomUUID().toString());
            dadosChecklist.setLoteId(loteId);
        });

        return dadosChecklistRepository.saveAll(listDadosChecklist);
    }
}
