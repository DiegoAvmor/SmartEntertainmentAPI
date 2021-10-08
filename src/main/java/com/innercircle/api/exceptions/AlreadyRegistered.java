package com.innercircle.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FOUND)
public class AlreadyRegistered extends RuntimeException {

    public AlreadyRegistered() {
        super("Ya existe un usuario registrado con ese correo.");
    }

    public AlreadyRegistered(String mensaje) {
        super(mensaje);
    }
    
}
