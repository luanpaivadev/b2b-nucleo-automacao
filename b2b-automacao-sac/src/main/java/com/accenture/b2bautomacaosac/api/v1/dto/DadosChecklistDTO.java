package com.accenture.b2bautomacaosac.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosChecklistDTO {

    private String uf;
    private String ddd;
    private String terminal;
    private String circuito;
    private String localidade;
    private String urlCallback;
    private String requestId;
    private String loteId;

}
