package com.accenture.b2bautomacaostc.domain.exceptions;

import java.io.Serializable;

public class ParametrosInvalidosExceptions extends Exception implements Serializable {

    public ParametrosInvalidosExceptions(String message) {
        super(message);
    }
}
