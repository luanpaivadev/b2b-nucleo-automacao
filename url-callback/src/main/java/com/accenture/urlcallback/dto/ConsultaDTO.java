package com.accenture.urlcallback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaDTO {

    private String descricao;
    private String sistema;
    private String status;
    private String mensagemStatus;
    private String dadosSaida;

}
