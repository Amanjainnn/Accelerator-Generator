package com.hackerearth.accelerator.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechStackRequest {

	@Valid
	@NotNull
	@NotEmpty
	String appName;

	@Valid
	@NotNull
	@NotEmpty
	String backend;

	@Valid
	@NotNull
	@NotEmpty
	String frontend;

	@Valid
	@NotNull
	Map<String, String> form;

}
