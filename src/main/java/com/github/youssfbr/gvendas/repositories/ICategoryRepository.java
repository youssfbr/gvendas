package com.github.youssfbr.gvendas.repositories;

import com.github.youssfbr.gvendas.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
