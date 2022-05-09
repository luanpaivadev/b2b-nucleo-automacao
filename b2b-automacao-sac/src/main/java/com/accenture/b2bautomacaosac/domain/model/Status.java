package com.accenture.b2bautomacaosac.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {

    SUCESSO(0),
    ERRO(1);

    @Getter
    private Integer status;

}
