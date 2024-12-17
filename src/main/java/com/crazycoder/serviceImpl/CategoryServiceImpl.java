package com.crazycoder.serviceImpl;

import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.crazycoder.dto.CategoryDto;
import com.crazycoder.dto.CategoryResponse;
import com.crazycoder.model.Category;
import com.crazycoder.repository.CategoryRepository;
import com.crazycoder.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		
		Category category=modelMapper.map(categoryDto, Category.class);
		
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
	public List<CategoryDto> getAllCategory() {
		
		List<Category> listCategories=categoryRepository.findAll();
		
		List<CategoryDto> listCategoryDtos =listCategories.stream()
				                           .map(cat->modelMapper.map(cat, CategoryDto.class))
				                           .toList();
		
		return listCategoryDtos;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> getAllActiveCategory=categoryRepository.findByIsActiveTrue();
		
		List<CategoryResponse> categoryResponses=getAllActiveCategory.stream()
				                                                  .map(cat->modelMapper.map(cat, CategoryResponse.class))
				                                                  .toList();
		return categoryResponses;
	}

}
