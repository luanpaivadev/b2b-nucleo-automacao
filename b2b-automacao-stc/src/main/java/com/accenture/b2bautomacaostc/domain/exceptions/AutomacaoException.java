package com.accenture.b2bautomacaostc.domain.exceptions;

import java.io.Serializable;

public class AutomacaoException extends Exception implements Serializable {

    public AutomacaoException() {
        super();
    }

    public AutomacaoException(String message) {
        super(message);
    }
}
