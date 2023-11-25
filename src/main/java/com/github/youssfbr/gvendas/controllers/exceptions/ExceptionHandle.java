package com.github.youssfbr.gvendas.controllers.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    private static final String NOT_BLANK = "NotBlank";
    private static final String LENGTH = "Length";
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex , HttpHeaders headers , HttpStatusCode status , WebRequest request) {

        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final List<Error> errors = getErrors(ex.getBindingResult());

        return handleExceptionInternal(ex , errors , headers , badRequest , request);
    }

    private List<Error> getErrors(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {

            String msgUser = Objects.requireNonNull(msgUserHandler(fieldError));
            String msgDeveloper = fieldError.toString();

            errors.add(new Error(msgUser , msgDeveloper)) ;
        });

        return errors;
    }

    private String msgUserHandler(FieldError fieldError) {
        if (Objects.equals(fieldError.getCode() , NOT_BLANK)) {
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if (Objects.equals(fieldError.getCode() , LENGTH)) {
            return fieldError.getDefaultMessage()
                    .concat(String.format(" deve ter entre %s e %s caracteres",
                    fieldError.getArguments()[2],
                    fieldError.getArguments()[1]));
        }
        return fieldError.toString();
    }
}
