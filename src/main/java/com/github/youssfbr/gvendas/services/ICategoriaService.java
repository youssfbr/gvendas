package com.github.youssfbr.gvendas.services;

import com.github.youssfbr.gvendas.entities.Categoria;

import java.util.List;

public interface ICategoriaService {
    List<Categoria> findAllCategories();
    Categoria findCategoryById(Long id);
    Categoria createCategory(Categoria categoria);
    Categoria updateCategory(Categoria categoria);
}
