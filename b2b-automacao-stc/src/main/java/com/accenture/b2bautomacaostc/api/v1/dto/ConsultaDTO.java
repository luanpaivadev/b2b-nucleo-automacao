package com.accenture.b2bautomacaostc.api.v1.dto;

import com.accenture.b2bautomacaostc.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaDTO {

    private String descricao;
    private String sistema;
    private Status status;
    private String mensagemStatus;
    private String dadosSaida;

}
