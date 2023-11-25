package com.github.youssfbr.gvendas.controllers.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex ,
            HttpHeaders headers ,
            HttpStatusCode status ,
            WebRequest request) {

        final StandardError build = StandardError
                .builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Um ou mais campos estão inválidos.")
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .errors(getErrors(ex.getBindingResult()))
                .build();

        return handleExceptionInternal(ex , build , headers , status , request);
    }

    private List<Error> getErrors(BindingResult bindingResult) {

        List<Error> errors = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {

            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errors.add(new Error(field , message)) ;
        });

        return errors;
    }

}
