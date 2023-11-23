package com.github.youssfbr.gvendas.services.impl;

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
    public List<Categoria> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    @Transactional
    public Categoria createCategory(Categoria categoria) {
        return categoryRepository.save(categoria);
    }
}
