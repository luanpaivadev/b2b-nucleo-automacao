package com.accenture.b2bautomacaoorquestrador.domain.repository;

import com.accenture.b2bautomacaoorquestrador.domain.model.DadosChecklist;

import java.util.List;

public interface EnviarMensagem {

    void send(DadosChecklist dadosChecklist);
    void send(List<DadosChecklist> dadosChecklists);

}
