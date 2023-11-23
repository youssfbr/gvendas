package com.github.youssfbr.gvendas.controllers;

import com.github.youssfbr.gvendas.entities.Categoria;
import com.github.youssfbr.gvendas.services.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAllCategories() {
        return ResponseEntity.ok(categoriaService.findAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findCategoryById(id));
    }

}
