package com.crazycoder.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.crazycoder.dto.CategoryDto;
import com.crazycoder.exception.dtoValidationException;

@Component
public class Validation {
    
	public void categoryDtoValidation(CategoryDto categoryDto) throws dtoValidationException {
		 
		Map<String, Object> exceptionContainer=new LinkedHashMap<>();
		
		if(ObjectUtils.isEmpty(categoryDto)) {
			throw new IllegalArgumentException("Category Object/JSON should not be empty or null");
		} else {
			
			//Check name field validation
			if(ObjectUtils.isEmpty(categoryDto.getName())) {
				exceptionContainer.put("name", "name field can not be empty");
			} else {
				if(categoryDto.getName().length()<10) {
					exceptionContainer.put("name", "name length is less than 10");
				}
				if(categoryDto.getName().length()>100) {
					exceptionContainer.put("name", "name length is grater than 100");
				}
			}
			
			//Check description field validation
			
			if(ObjectUtils.isEmpty(categoryDto.getDescription())) {
				exceptionContainer.put("description", "Description can not be null");
			}
			
			//Check isActive field validation
			
			if(ObjectUtils.isEmpty(categoryDto.getIsActive())) {
				exceptionContainer.put("isActive", "isActive field can not be null");
			} else {
				if(categoryDto.getIsActive()!=Boolean.FALSE.booleanValue() && categoryDto.getIsActive()!=Boolean.TRUE.booleanValue()) {
					exceptionContainer.put("isActive", "Invalid isActive filed, Only accetptable is TRUE/FALSE");
				}
			}
		}
		
		if(!ObjectUtils.isEmpty(exceptionContainer)) {
			throw new dtoValidationException(exceptionContainer);
		}
	}
}
