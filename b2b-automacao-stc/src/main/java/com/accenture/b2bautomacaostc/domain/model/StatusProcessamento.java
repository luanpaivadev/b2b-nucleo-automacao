package com.accenture.b2bautomacaostc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusProcessamento {

    PENDENTE(0),
    PROCESSADO(1);

    @Getter
    private Integer statusProcessamento;
}
