package com.innercircle.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(){
        super("Acceso denegado");
    }

    public AccessDeniedException(String message){
        super(message);
    }
}
