package com.github.youssfbr.gvendas.controllers.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<Error> errors;
}
