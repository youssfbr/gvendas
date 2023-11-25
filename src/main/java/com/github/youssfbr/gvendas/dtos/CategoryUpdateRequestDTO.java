package com.github.youssfbr.gvendas.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CategoryUpdateRequestDTO {

    private Long id;

    @NotBlank
    @Length(min = 3, max = 50)
    private String name;
}
