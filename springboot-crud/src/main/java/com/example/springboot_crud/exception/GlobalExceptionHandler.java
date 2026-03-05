package com.example.springboot_crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.springboot_crud.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleAllOtherExceptions(Exception ex) {

		log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
				.body(ApiResponse.error("An unexpected error occurred. Please try again later."));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {

		log.error("Resource not found: {}", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND) // 404
				.body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiResponse<Object>> handleDuplicateEmail(DuplicateEmailException ex) {

		log.error("Duplicate email: {}", ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT) // 409
				.body(ApiResponse.error(ex.getMessage()));
	}
}
