package com.accenture.b2bautomacaosac.domain.exceptions;

import java.io.Serializable;

public class EntidadeNaoEncontradaException extends Exception implements Serializable {

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
