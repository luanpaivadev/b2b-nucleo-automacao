package com.accenture.b2bautomacaoorquestrador.api.v1.model.dto;

import com.accenture.b2bautomacaoorquestrador.domain.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private String mensagem;
    private Status status;
    private String requestId;
    private String loteId;

}
