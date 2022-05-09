package com.accenture.b2bautomacaostc.domain.exceptions;

import java.io.Serializable;

public class UsuarioException extends Exception implements Serializable {

    public UsuarioException() {
        super();
    }

    public UsuarioException(String message) {
        super(message);
    }
}
