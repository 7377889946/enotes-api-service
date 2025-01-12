package com.crazycoder.serviceImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.crazycoder.dto.NotesDto;
import com.crazycoder.exception.ResourceNotFoundException;
import com.crazycoder.model.FileDetails;
import com.crazycoder.model.Notes;
import com.crazycoder.repository.CategoryRepository;
import com.crazycoder.repository.FileDetailsRepository;
import com.crazycoder.repository.NotesRepository;
import com.crazycoder.service.NotesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.model.Model;

@Service
public class NotesServiceImpl implements NotesService{
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private  FileDetailsRepository fileDetailsRepository;
    
	@Value("${file.upload.path}")
	private String uploadpath;
	
	@Override
	public Boolean saveNotes(String notesDto, MultipartFile file) throws ResourceNotFoundException, IOException {
		//Now notes came in String format we need to convert in NotesDto using ObjectMapper 
          ObjectMapper objectMapper=new ObjectMapper(); // 
          NotesDto notesDto1=objectMapper.readValue(notesDto,NotesDto.class);
	
		//Validation check whether category is exists is not in category table
		categoryRepository.findByIdAndIsDeletedFalse(notesDto1.getCategory().getId())
		                  .orElseThrow(()-> new ResourceNotFoundException("Category id not exist"));
		
		Notes note=modelMapper.map(notesDto1, Notes.class);
		
		
		//File store in local file and file object is created
		FileDetails fileDetails=saveFileDetails(file);
		if(!ObjectUtils.isEmpty(fileDetails)) {
		    note.setFilesDetails(fileDetails);
		} else {
			note.setFilesDetails(null);
		}
		
		Notes savedNotes=notesRepository.save(note);
		if(ObjectUtils.isEmpty(savedNotes)) {
			return false;
		} else {
			return true;
		}
	}

	private FileDetails saveFileDetails(MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			
			//FileExtension check whether its correct file received or not.
			List<String> extensionList=Arrays.asList("pdf","xlsx","docs","png");
			
			
			String extension=FilenameUtils.getExtension(file.getOriginalFilename());
			
			if(!extensionList.contains(extension)) {
				throw new IllegalArgumentException("File Extension only allowed [pdf,xlsx,docs]");
			}
			
			//create random string for uploaded filename
			String randomFile=UUID.randomUUID().toString();
			String uploadFileName=randomFile+"."+extension;
			
			File pathFile=new File(uploadpath);
			
			if(!pathFile.exists()) {
				pathFile.mkdir();
			}
			
			String storePath=uploadpath.concat(uploadFileName);
			
			//uploadFile;
			long uploadInLocalfile=Files.copy(file.getInputStream(),Paths.get(storePath));
			
			if(uploadInLocalfile!=0) {
				FileDetails fileDetails=new FileDetails();
				fileDetails.setOriginalFileName(file.getOriginalFilename());
				fileDetails.setDisplayFileName(displayFileName(file.getOriginalFilename()));
				fileDetails.setFileSize(file.getSize());
				fileDetails.setUploadFileName(uploadFileName);
				fileDetails.setPath(storePath);
				FileDetails savedFile=fileDetailsRepository.save(fileDetails);
				return savedFile;	
			}
		}
		return null;
	}

	private String displayFileName(String file) {
		String extension=FilenameUtils.getExtension(file);
		String filename=FilenameUtils.removeExtension(file);
		
		if(filename.length()>8) {
			filename=filename.substring(0,7);
		}
		
	   return filename+"."+extension;
	}

	@Override
	public List<NotesDto> getAllNotes() {
       return notesRepository.findAll().stream().map(note -> modelMapper.map(note, NotesDto.class)).toList();              
	} 
}
