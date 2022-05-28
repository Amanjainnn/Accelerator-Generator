package com.hackerearth.accelerator.generator.exception;

import com.hackerearth.accelerator.generator.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponseDto> handleValidationException(BadRequestException ex) {
		log.error("Bad Request", ex);
		return new ResponseEntity<>(new ErrorResponseDto(String.format("Bad Request: %s", ex.getMessage())),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
		log.error("Some Error Occurred", ex);

		return new ResponseEntity<>(new ErrorResponseDto("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
