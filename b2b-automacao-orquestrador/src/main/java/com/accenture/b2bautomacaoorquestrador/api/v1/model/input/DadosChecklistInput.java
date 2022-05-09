package com.accenture.b2bautomacaoorquestrador.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DadosChecklistInput {

    private String uf;
    private String ddd;
    private String terminal;
    private String circuito;
    private String localidade;
    @NotBlank
    private String cliente;
    @NotBlank
    private String urlCallback;
    @NotNull
    private List<ConsultaInput> consultas = new ArrayList<>();
}
