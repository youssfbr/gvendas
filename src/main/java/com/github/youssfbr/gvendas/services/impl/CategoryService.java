package com.github.youssfbr.gvendas.services.impl;

import com.github.youssfbr.gvendas.dtos.CategoryCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoryResponseDTO;
import com.github.youssfbr.gvendas.dtos.CategoryUpdateRequestDTO;
import com.github.youssfbr.gvendas.entities.Category;
import com.github.youssfbr.gvendas.repositories.ICategoryRepository;
import com.github.youssfbr.gvendas.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private static final String NOT_FOUND_MESSAGE = "Resource not found with id ";

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryResponseDTO::new)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE + id));

    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateRequestDTO categoryCreateRequestDTO) {

        final Category categoryToSave = new Category(categoryCreateRequestDTO);

        final Category categorySaved = categoryRepository.save(categoryToSave);

        return new CategoryResponseDTO(categorySaved);
    }

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(CategoryUpdateRequestDTO categoryUpdateRequestDTO) {

        final Category categoryToUpdate = checkCategoryExistsById(categoryUpdateRequestDTO.getId());
        categoryToUpdate.setName(categoryUpdateRequestDTO.getName());

        final Category categoryUpdated = categoryRepository.save(categoryToUpdate);

        return new CategoryResponseDTO(categoryUpdated);
    }

    private Category checkCategoryExistsById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE + id));
    }
}
