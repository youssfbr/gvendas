package com.github.youssfbr.gvendas.controllers;

import com.github.youssfbr.gvendas.dtos.CategoryCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoryResponseDTO;
import com.github.youssfbr.gvendas.dtos.CategoryUpdateRequestDTO;
import com.github.youssfbr.gvendas.services.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@Tag(name = "Categoria")
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoriaService;

    @GetMapping
    @Operation(summary = "Listar", description = "Lista todas as categorias")
    public ResponseEntity<List<CategoryResponseDTO>> findAllCategories() {
        return ResponseEntity.ok(categoriaService.findAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar por ID", description = "Lista uma categoria pelo ID (Número)")
    public ResponseEntity<CategoryResponseDTO> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findCategoryById(id));
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Salva uma categoria")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryCreateRequestDTO categoryCreateRequestDTO) {

        CategoryResponseDTO categoryCreated = categoriaService.createCategory(categoryCreateRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoryCreated);
    }

    @PutMapping
    @Operation(summary = "Atualizar", description = "Atualiza uma categoria")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryUpdateRequestDTO categoryUpdateRequestDTO) {
        return ResponseEntity.ok(categoriaService.updateCategory(categoryUpdateRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Exclui uma categoria")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoriaService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
