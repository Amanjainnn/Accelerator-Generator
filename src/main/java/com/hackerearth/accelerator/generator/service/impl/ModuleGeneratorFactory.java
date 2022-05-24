package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

@UtilityClass
public class ModuleGeneratorFactory {

	public static void generate(Path path, TechStackRequest request, String module)
			throws IOException, InterruptedException {

		switch (module.toLowerCase(Locale.ROOT)) {
		case "spring boot":
			new SpringBootModuleGenerator().generate(path, request);
			break;
		case "angular":
			new AngularModuleGenerator().generate(path, request);
			break;
		case "node":
			new NodeModuleGenerator().generate(path, request);
			break;
		default:
			throw new BadRequestException("Invalid Module type");

		}

	}

}
