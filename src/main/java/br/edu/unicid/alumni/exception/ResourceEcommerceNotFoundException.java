package br.edu.unicid.alumni.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceEcommerceNotFoundException extends RuntimeException {

    public ResourceEcommerceNotFoundException(String message){
        super(message);
    }
}
