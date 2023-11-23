package com.github.youssfbr.gvendas.controllers;

import com.github.youssfbr.gvendas.dtos.CategoriaResponseDTO;
import com.github.youssfbr.gvendas.entities.Categoria;
import com.github.youssfbr.gvendas.services.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> findAllCategories() {
        return ResponseEntity.ok(categoriaService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategory(@RequestBody Categoria categoria) {

        Categoria categoryCreated = categoriaService.createCategory(categoria);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoryCreated);
    }

    @PutMapping
    public ResponseEntity<Categoria> updateCategory(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.updateCategory(categoria));
    }
}
