package br.edu.unicid.alumni.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<ExceptionResponse> handleException(Exception ex, WebRequest request, HttpStatus status) {
        ExceptionResponse exceptionResponse = buildExceptionResponse(ex, request);
        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler(ResourceEcommerceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ExceptionResponse> handleRequestException(Exception ex, WebRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceEcommerceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }


    private ExceptionResponse buildExceptionResponse(Exception ex, WebRequest request) {
        return new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    }
}
