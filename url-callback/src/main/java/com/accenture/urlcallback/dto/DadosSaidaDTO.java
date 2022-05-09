package com.accenture.urlcallback.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DadosSaidaDTO {

    private DadosChecklistDTO dadosChecklist;
    private List<ConsultaDTO> consultas;

}
