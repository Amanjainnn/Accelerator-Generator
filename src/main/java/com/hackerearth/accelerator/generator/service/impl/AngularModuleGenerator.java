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
        Utilities.printResults(process);
        process.waitFor();

        if (process.exitValue() != 0) {
            throw new RuntimeException("Error Generating frontend");
        }
    }

    }
