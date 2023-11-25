package com.github.youssfbr.gvendas.dtos;

import com.github.youssfbr.gvendas.entities.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDTO {

    private Long id;
    private String name;

    public CategoryResponseDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
