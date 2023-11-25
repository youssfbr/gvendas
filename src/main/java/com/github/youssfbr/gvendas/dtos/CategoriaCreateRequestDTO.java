package com.github.youssfbr.gvendas.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CategoriaCreateRequestDTO {

    @NotBlank(message = "O Nome")
    @Length(min = 3, max = 50, message = "O Nome")
    private String nome;
}
