package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import com.hackerearth.accelerator.generator.service.ModuleGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Service
public class AngularModuleGenerator implements ModuleGenerator {

    @Override
    public void generate(Path path, TechStackRequest request)
            throws BadRequestException, IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder();
        Map<String, String> env = pb.environment();
        env.put("appName", request.getAppName() + "-frontend");
        env.put("appDirectory", path.toString());
        env.put("openAPIJarPath", Path.of(new ClassPathResource("openapi-generator-cli.jar").getURI()).toString());
        env.put("angularFiles", Path.of(new ClassPathResource("angular").getURI()).toString());
        env.put("defaultYamlPath", Path.of(new ClassPathResource("default.yml").getURI()).toString());

        pb.command(Arrays.asList("python", Path.of(new ClassPathResource("appgenerator.py").getURI()).toString()));
        Process process = pb.start();
        printResults(process);
        process.waitFor();

		if (process.exitValue() != 0) {
			throw new RuntimeException("Error Generating frontend");
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
        } catch (IOException exception) {
            log.error("Error printing message");
        }

    }
}
