package com.github.youssfbr.gvendas.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<Error> errors;
}
