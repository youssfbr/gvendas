package com.github.youssfbr.gvendas.services.impl;

import com.github.youssfbr.gvendas.dtos.CategoryCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoryResponseDTO;
import com.github.youssfbr.gvendas.dtos.CategoryUpdateRequestDTO;
import com.github.youssfbr.gvendas.entities.Category;
import com.github.youssfbr.gvendas.repositories.ICategoryRepository;
import com.github.youssfbr.gvendas.services.ICategoryService;
import com.github.youssfbr.gvendas.services.exceptions.ResourceAlreadyExistsException;
import com.github.youssfbr.gvendas.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private static final String NOT_FOUND_MESSAGE = "Resource not found with id ";

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAllCategories() {
        return categoryRepository.findAllByActiveTrue()
                .stream()
                .map(CategoryResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO findCategoryById(Long id) {
        return categoryRepository.findByIdAndActiveTrue(id)
                .map(CategoryResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryCreateRequestDTO categoryCreateRequestDTO) {

        checkCategoryExistsByName(categoryCreateRequestDTO.getName());

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

    @Override
    @Transactional
    public void deleteCategory(Long id) {

        Category category = checkCategoryExistsById(id);

        if (Boolean.FALSE.equals(category.getActive()))
            throw new ResourceNotFoundException(NOT_FOUND_MESSAGE + id);

        category.setActive(false);

        categoryRepository.save(category);
    }

    private Category checkCategoryExistsById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    private void checkCategoryExistsByName(String name) {

        final Optional<Category> cat = categoryRepository.findByName(name)
                .stream()
                .filter(x -> x.getName().equals(name))
                .findAny();

        if (cat.isPresent() && Boolean.TRUE.equals(cat.get().getActive()))
            throw new ResourceAlreadyExistsException(String.format("A Categoria %s já existe." , name.toUpperCase()));
    }
}
