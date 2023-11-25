package com.github.youssfbr.gvendas.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    private String field;
    private String message;
}
