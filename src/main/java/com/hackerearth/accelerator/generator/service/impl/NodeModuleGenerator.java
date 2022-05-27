package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import com.hackerearth.accelerator.generator.service.ModuleGenerator;
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
				+ path.resolve(request.getAppName() + "Backend");

		Process process = Runtime.getRuntime().exec("java -jar "
				+ Path.of(new ClassPathResource("openapi-generator-cli.jar").getURI()) + commandToGenerateServer);
		printResults(process);
		process.waitFor();
		if (process.exitValue() != 0) {
			throw new RuntimeException("Error Generating backend");
		}
	}

	public static void printResults(Process process) throws IOException {
		try (InputStream inputStream = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream);
				InputStream errorStream = process.getErrorStream();
				InputStreamReader esr = new InputStreamReader(errorStream)) {

			int n1;
			char[] c1 = new char[1024];
			StringBuilder stableOutput = new StringBuilder();
			while ((n1 = isr.read(c1)) > 0) {
				stableOutput.append(c1, 0, n1);
			}
			log.debug("Stable Output: " + stableOutput);

			int n2;
			char[] c2 = new char[1024];
			StringBuilder stableError = new StringBuilder();
			while ((n2 = esr.read(c2)) > 0) {
				stableError.append(c2, 0, n2);
			}
			log.error("Stable Error: " + stableError.toString());
		}
		catch (IOException exception) {
			log.error("Error printing message");
		}

	}

}
