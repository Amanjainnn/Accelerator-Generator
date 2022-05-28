package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import com.hackerearth.accelerator.generator.service.ModuleGenerator;
import com.hackerearth.accelerator.generator.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

@Slf4j
@Service
public class NodeModuleGenerator implements ModuleGenerator {

	@Override
	public void generate(Path path, TechStackRequest request)
			throws BadRequestException, IOException, InterruptedException {
		String commandToGenerateServer = " generate" + " -g nodejs-express-server " + " -i "
				+ Path.of(new ClassPathResource("default.yml").getURI()) + " -o "
				+ path.resolve(request.getAppName() + "-backend");

		Process process = Runtime.getRuntime().exec("java -jar "
				+ Path.of(new ClassPathResource("openapi-generator-cli.jar").getURI()) + commandToGenerateServer);
		Utilities.printResults(process);
		process.waitFor();
		if (process.exitValue() != 0) {
			throw new RuntimeException("Error Generating backend");
		}
	}


}
