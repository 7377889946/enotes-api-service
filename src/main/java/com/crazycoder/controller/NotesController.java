package com.crazycoder.controller;
import java.io.IOException;
import java.util.List;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.crazycoder.commonUtil.CommonUtil;
import com.crazycoder.dto.NotesDto;
import com.crazycoder.exception.ResourceNotFoundException;
import com.crazycoder.service.NotesService;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
	
	@Autowired
	private NotesService notesService;
     
	@PostMapping("/save-notes")
	 public ResponseEntity<?> saveNotes(@RequestParam String notesDto,@RequestParam MultipartFile file) throws ResourceNotFoundException, IOException{
		 Boolean savedNotes=notesService.saveNotes(notesDto,file);
		
		 if(savedNotes) {
			 return CommonUtil.createBuildResponseMessage("Notes saved Successfully",HttpStatus.OK);
		 } else {
			 return CommonUtil.createErrorResponseMessage("Notes not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	
	@GetMapping
	public ResponseEntity<?> getAllNotes(){
		List<NotesDto> notesDtos=notesService.getAllNotes();
		
		if(org.springframework.util.CollectionUtils.isEmpty(notesDtos)) {
		       return  ResponseEntity.noContent().build();
		} else {
			   return CommonUtil.createBuildResponse(notesDtos, HttpStatus.OK);
		}
	}
	
	
}
