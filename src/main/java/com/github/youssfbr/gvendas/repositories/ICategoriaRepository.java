package com.github.youssfbr.gvendas.repositories;

import com.github.youssfbr.gvendas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
