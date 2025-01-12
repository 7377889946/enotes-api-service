package com.crazycoder.commonUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.crazycoder.handler.GenericResponse;

public class CommonUtil {
	
	//if API getting success 
	public static ResponseEntity<?> createBuildResponse(Object data,HttpStatus status){
          GenericResponse genericResponse=GenericResponse.builder()
        		  .statusCode(status)
        		  .status("Success")
        		  .message("message")
        		  .data(data)
        		  .build();
          return genericResponse.create();
	}
	
	public static ResponseEntity<?> createBuildResponseMessage(String message,HttpStatus status){
        GenericResponse genericResponse=GenericResponse.builder()
      		  .statusCode(status)
      		  .status("Success")
      		  .message(message)
      		  .build();
        return genericResponse.create();
	}
	
	public static ResponseEntity<?> createErrorResponse(Object data,HttpStatus status){
        GenericResponse genericResponse=GenericResponse.builder()
      		  .statusCode(status)
      		  .status("Failed")
      		  .data(data)
      		  .message("Failed")
      		  .build();
        return genericResponse.create();
	}
	
	public static ResponseEntity<?> createErrorResponseMessage(String message,HttpStatus status){
        GenericResponse genericResponse=GenericResponse.builder()
      		  .statusCode(status)
      		  .status("Failed")
      		  .message(message)
      		  .build();
        return genericResponse.create();
	}
	 
	
	

}
