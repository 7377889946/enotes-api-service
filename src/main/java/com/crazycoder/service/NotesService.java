package com.crazycoder.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.crazycoder.dto.NoteByPageDto;
import com.crazycoder.dto.NotesDto;
import com.crazycoder.exception.ResourceNotFoundException;
import com.crazycoder.model.FileDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface NotesService {
	
	public Boolean saveNotes(String notesDto,MultipartFile file) throws ResourceNotFoundException, JsonMappingException, JsonProcessingException, IOException;
	public List<NotesDto> getAllNotes();
	public byte[] downloadFile(FileDetails fileDetails) throws ResourceNotFoundException, FileNotFoundException, IOException;
	public FileDetails getFileDetils(Integer id) throws ResourceNotFoundException;
	public NoteByPageDto getAllNotesByUser(Integer user_id, Integer pageNo, Integer pageSize);
}
