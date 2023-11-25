package com.github.youssfbr.gvendas.controllers;

import com.github.youssfbr.gvendas.dtos.CategoriaCreateRequestDTO;
import com.github.youssfbr.gvendas.dtos.CategoriaResponseDTO;
import com.github.youssfbr.gvendas.dtos.CategoriaUpdateRequestDTO;
import com.github.youssfbr.gvendas.services.ICategoriaService;
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
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todas as categorias")
    public ResponseEntity<List<CategoriaResponseDTO>> findAllCategories() {
        return ResponseEntity.ok(categoriaService.findAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar por ID", description = "Listar uma categoria pelo ID (Número)")
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findCategoryById(id));
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Salvar uma categoria")
    public ResponseEntity<CategoriaResponseDTO> createCategory(@Valid @RequestBody CategoriaCreateRequestDTO categoriaCreateRequestDTO) {

        CategoriaResponseDTO categoryCreated = categoriaService.createCategory(categoriaCreateRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoryCreated);
    }

    @PutMapping
    @Operation(summary = "Atualizar", description = "Atualiza uma categoria")
    public ResponseEntity<CategoriaResponseDTO> updateCategory(@Valid @RequestBody CategoriaUpdateRequestDTO categoriaUpdateRequestDTO) {
        return ResponseEntity.ok(categoriaService.updateCategory(categoriaUpdateRequestDTO));
    }
}
