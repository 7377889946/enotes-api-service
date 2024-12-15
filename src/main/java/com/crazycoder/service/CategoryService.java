package com.crazycoder.service;

import java.util.List;

import com.crazycoder.model.Category;

public interface CategoryService {

	public Boolean saveCategory(Category category);
	public List<Category> getAllCategory();
	
}
