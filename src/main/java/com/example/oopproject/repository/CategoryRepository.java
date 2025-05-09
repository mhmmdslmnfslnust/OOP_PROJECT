package com.example.oopproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oopproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
