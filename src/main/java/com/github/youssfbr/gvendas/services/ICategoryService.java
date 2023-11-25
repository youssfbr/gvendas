package com.github.youssfbr.gvendas.services;

import com.github.youssfbr.gvendas.dtos.CategoryCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoryResponseDTO;
import com.github.youssfbr.gvendas.dtos.CategoryUpdateRequestDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDTO> findAllCategories();
    CategoryResponseDTO findCategoryById(Long id);
    CategoryResponseDTO createCategory(CategoryCreateRequestDTO categoryCreateRequestDTO);
    CategoryResponseDTO updateCategory(CategoryUpdateRequestDTO categoryUpdateRequestDTO);
}
