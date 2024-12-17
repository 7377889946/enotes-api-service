package com.crazycoder.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
		
		List<Category> listCategories=categoryRepository.findByIsDeletedFalse();
		
		List<CategoryDto> listCategoryDtos =listCategories.stream()
				                           .map(cat->modelMapper.map(cat, CategoryDto.class))
				                           .toList();
		
		return listCategoryDtos;
	}

	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> getAllActiveCategory=categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
		
		List<CategoryResponse> categoryResponses=getAllActiveCategory.stream()
				                                                  .map(cat->modelMapper.map(cat, CategoryResponse.class))
				                                                  .toList();
		return categoryResponses;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
	Optional<Category> categoryById=categoryRepository.findByIdAndIsDeletedFalse(id);
	
	  if(categoryById.isPresent()) {
		  Category category=categoryById.get();
		  CategoryDto categoryDto=modelMapper.map(category, CategoryDto.class);
		  return categoryDto;
		  
	  } else {
		  return null;
	  }
		
	}

	@Override
	public Boolean deleteCategoryById(Integer id) {
		Optional<Category> getCategoryById=categoryRepository.findById(id);
		
		if(getCategoryById.isPresent()) {
			Category category=getCategoryById.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
			return true;
		}
		return false;
	}

}
