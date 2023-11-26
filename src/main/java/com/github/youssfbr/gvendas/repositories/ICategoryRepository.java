package com.github.youssfbr.gvendas.repositories;

import com.github.youssfbr.gvendas.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByActiveTrue();
    Optional<Category> findByIdAndActiveTrue(Long id);
    List<Category> findByName(String name);
}
