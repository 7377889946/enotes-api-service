package com.crazycoder.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.crazycoder.model.Category;
import com.crazycoder.repository.CategoryRepository;
import com.crazycoder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Boolean saveCategory(Category category) {
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category savedCategory=categoryRepository.save(category);
		if(ObjectUtils.isEmpty(savedCategory)) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public List<Category> getAllCategory() {
		
		List<Category> listCategories=categoryRepository.findAll();
		
		return listCategories;
	}

}
