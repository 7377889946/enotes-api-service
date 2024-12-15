package com.crazycoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazycoder.model.Category;
import com.crazycoder.service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/save-category")
	 public ResponseEntity<?> saveCategory(@RequestBody Category category){
		Boolean savedCategory=categoryService.saveCategory(category);
		
		if(savedCategory) {
			return new ResponseEntity<>("saved Successfully",HttpStatus.OK);
		} else{
		  return new ResponseEntity<>("Not saved",HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> getAllCategory(){
		List<Category> categories=categoryService.getAllCategory();
		if(org.springframework.util.CollectionUtils.isEmpty(categories)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(categories,HttpStatus.OK);
		}
	}

}
