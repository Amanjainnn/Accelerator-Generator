package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import com.hackerearth.accelerator.generator.service.ModuleGenerator;

import java.nio.file.Path;

public class AngularModuleGenerator implements ModuleGenerator {

	@Override
	public void generate(Path path, TechStackRequest request) throws BadRequestException {
	}

}
