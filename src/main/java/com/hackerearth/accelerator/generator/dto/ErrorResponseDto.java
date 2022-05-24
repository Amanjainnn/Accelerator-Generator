package com.hackerearth.accelerator.generator.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

	private String message;

	private LocalDateTime dateTime;

	public ErrorResponseDto(String message) {
		this.message = message;
		this.dateTime = LocalDateTime.now();
	}

}
