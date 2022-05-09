package com.accenture.b2bautomacaostc.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@Getter
@Setter
public class DadosSaidaDTO {

    private DadosChecklistDTO dadosChecklist;
    private Queue<ConsultaDTO> consultas;

}
