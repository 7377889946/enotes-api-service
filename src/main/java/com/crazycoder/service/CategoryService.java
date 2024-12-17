package com.crazycoder.service;

import java.util.List;

import com.crazycoder.dto.CategoryDto;
import com.crazycoder.dto.CategoryResponse;
import com.crazycoder.model.Category;

public interface CategoryService {

	public Boolean saveCategory(CategoryDto categoryDto);
	public List<CategoryDto> getAllCategory();
	public List<CategoryResponse> getActiveCategory();
	public CategoryDto getCategoryById(Integer id);
	public Boolean deleteCategoryById(Integer id);
	
}
