package com.github.youssfbr.gvendas.services.impl;

import com.github.youssfbr.gvendas.dtos.CategoriaCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoriaResponseDTO;
import com.github.youssfbr.gvendas.dtos.CategoriaUpdateRequestDTO;
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
    public CategoriaResponseDTO createCategory(CategoriaCreateRequestDTO categoriaCreateRequestDTO) {

        final Categoria categoriaToSave = new Categoria(categoriaCreateRequestDTO);

        final Categoria categoriaSaved = categoryRepository.save(categoriaToSave);

        return new CategoriaResponseDTO(categoriaSaved);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO updateCategory(CategoriaUpdateRequestDTO categoriaUpdateRequestDTO) {

        final Categoria categoryToUpdate = checkCategoryExistsById(categoriaUpdateRequestDTO.getId());
        categoryToUpdate.setNome(categoriaUpdateRequestDTO.getNome());

        final Categoria categoriaUpdated = categoryRepository.save(categoryToUpdate);

        return new CategoriaResponseDTO(categoriaUpdated);
    }

    private Categoria checkCategoryExistsById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE + id));
    }
}
