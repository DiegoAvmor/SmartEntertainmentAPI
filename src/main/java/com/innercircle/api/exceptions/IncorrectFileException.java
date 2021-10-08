package com.innercircle.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectFileException extends RuntimeException{
    public IncorrectFileException(){
        super("El archivo no es un video mp4");
    }

}
