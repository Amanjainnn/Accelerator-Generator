package com.hackerearth.accelerator.generator.validator;

import com.hackerearth.accelerator.generator.dto.Metadata;
import com.hackerearth.accelerator.generator.dto.TechStackConfig;
import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class RequestValidator {

	public void validateRequest(TechStackRequest request, Metadata metadata) throws BadRequestException {

		if (!metadata.getBackends().contains(request.getBackend()))
			throw new BadRequestException("Invalid backend");
		TechStackConfig techStackByBackend = getTechStackByBackend(request.getBackend(), metadata);

		if (!techStackByBackend.getFrontend().contains(request.getFrontend()))
			throw new BadRequestException("Invalid frontend");

		if (!techStackByBackend.getForm().keySet().equals(request.getForm().keySet()))
			throw new BadRequestException("Invalid frontend");
	}

	TechStackConfig getTechStackByBackend(String backend, Metadata metadata) {
		return metadata.getFrameworks().stream().filter(techStack -> techStack.getBackend().equals(backend)).findFirst()
				.orElseThrow();
	}

}
