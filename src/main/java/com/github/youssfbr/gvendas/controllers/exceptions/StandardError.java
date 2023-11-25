package com.github.youssfbr.gvendas.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<Error> errors = new ArrayList<>();
}
