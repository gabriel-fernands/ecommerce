package br.edu.unicid.alumni.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceEcommerceBadRequestException extends RuntimeException{
    public ResourceEcommerceBadRequestException(String message){
        super(message);
    }
}
