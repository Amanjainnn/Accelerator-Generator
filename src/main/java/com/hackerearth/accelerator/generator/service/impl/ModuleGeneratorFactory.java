package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

@Service
public class ModuleGeneratorFactory {

    @Autowired
    SpringBootModuleGenerator springBootModuleGenerator;

    @Autowired
    AngularModuleGenerator angularModuleGenerator;

    @Autowired
    NodeModuleGenerator nodeModuleGenerator;

    @Autowired
    FlaskModuleGenerator flaskModuleGenerator;

    public void generate(Path path, TechStackRequest request, String module) throws IOException, InterruptedException {

        switch (module.toLowerCase(Locale.ROOT)) {
            case "spring boot":
                springBootModuleGenerator.generate(path, request);
                break;
            case "angular":
                angularModuleGenerator.generate(path, request);
                break;
            case "node":
                nodeModuleGenerator.generate(path, request);
                break;
            case "flask":
                flaskModuleGenerator.generate(path, request);
                break;
            default:
                throw new BadRequestException("Invalid Module type");

        }

    }

}
