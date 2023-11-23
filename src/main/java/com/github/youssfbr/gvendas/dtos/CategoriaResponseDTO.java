package com.github.youssfbr.gvendas.dtos;

import com.github.youssfbr.gvendas.entities.Categoria;
import lombok.Getter;

@Getter
public class CategoriaResponseDTO {

    private Long id;
    private String nome;

    public CategoriaResponseDTO(Categoria entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
}
