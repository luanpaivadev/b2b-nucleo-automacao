package com.accenture.b2bautomacaoorquestrador.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ConsultaInput {

    @NotBlank
    private String descricao;
    @NotBlank
    private String sistema;

}
