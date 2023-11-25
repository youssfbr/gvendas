package com.github.youssfbr.gvendas.repositories;

import com.github.youssfbr.gvendas.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByIsActiveTrue();
    Optional<Category> findByIdAndIsActiveTrue(Long id);
}
