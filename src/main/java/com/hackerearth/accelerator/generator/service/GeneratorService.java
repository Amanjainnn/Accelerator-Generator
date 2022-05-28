package com.hackerearth.accelerator.generator.service;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;

import java.io.IOException;
import java.nio.file.Path;

public interface GeneratorService {

	Path generate(TechStackRequest request) throws BadRequestException, IOException, InterruptedException;

}
