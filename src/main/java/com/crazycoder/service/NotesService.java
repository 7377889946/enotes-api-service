package com.crazycoder.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.crazycoder.dto.NotesDto;
import com.crazycoder.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface NotesService {
	
	public Boolean saveNotes(String notesDto,MultipartFile file) throws ResourceNotFoundException, JsonMappingException, JsonProcessingException, IOException;
	public List<NotesDto> getAllNotes();
	

}
