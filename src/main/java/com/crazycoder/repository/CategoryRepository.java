package com.crazycoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crazycoder.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

	List<Category> findByIsActiveTrue();
    
	
}
