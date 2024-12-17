package com.crazycoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazycoder.dto.CategoryDto;
import com.crazycoder.dto.CategoryResponse;
import com.crazycoder.model.Category;
import com.crazycoder.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/save-category")
	 public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
		Boolean savedCategory=categoryService.saveCategory(categoryDto);
		
		if(savedCategory) {
			return new ResponseEntity<>("saved Successfully",HttpStatus.OK);
		} else{
		  return new ResponseEntity<>("Not saved",HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllCategory(){
		List<CategoryDto> categories=categoryService.getAllCategory();
		if(org.springframework.util.CollectionUtils.isEmpty(categories)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(categories,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/active")
	public ResponseEntity<?> getActiveCategory(){
		List<CategoryResponse> categories=categoryService.getActiveCategory();
		if(org.springframework.util.CollectionUtils.isEmpty(categories)) {
			return ResponseEntity.noContent().build();
		} else { 
			return new ResponseEntity<>(categories,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id){
		CategoryDto categoryDto=categoryService.getCategoryById(id);
		if(ObjectUtils.isEmpty(categoryDto)) {
			return new ResponseEntity<>("Object Not Found with Id :- "+id,HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(categoryDto,HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
		Boolean deleted=categoryService.deleteCategoryById(id);
		
		if(deleted) {
			return new ResponseEntity<>("Category Deleted Successfully",HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Category did not Delete",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
