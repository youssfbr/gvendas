package com.github.youssfbr.gvendas.services.impl;

import com.github.youssfbr.gvendas.dtos.CategoriaResponseDTO;
import com.github.youssfbr.gvendas.entities.Categoria;
import com.github.youssfbr.gvendas.repositories.ICategoriaRepository;
import com.github.youssfbr.gvendas.services.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository categoryRepository;
    private static final String NOT_FOUND_MESSAGE = "Resource not found with id ";

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoriaResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoriaResponseDTO::new)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE + id));

    }

    @Override
    @Transactional
    public Categoria createCategory(Categoria categoria) {
        return categoryRepository.save(categoria);
    }

    @Override
    @Transactional
    public Categoria updateCategory(Categoria categoria) {

        final Categoria categoryToUpdate = checkCategoryExistsById(categoria.getId());

        categoryToUpdate.setNome(categoria.getNome());

        return categoryRepository.save(categoryToUpdate);
    }

    private Categoria checkCategoryExistsById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE + id));
    }
}
