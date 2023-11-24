package com.github.youssfbr.gvendas.services;

import com.github.youssfbr.gvendas.dtos.CategoriaCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoriaResponseDTO;
import com.github.youssfbr.gvendas.entities.Categoria;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaResponseDTO> findAllCategories();
    CategoriaResponseDTO findCategoryById(Long id);
    CategoriaResponseDTO createCategory(CategoriaCreateRequestDTO categoriaCreateRequestDTO);
    Categoria updateCategory(Categoria categoria);
}
