package com.hackerearth.accelerator.generator.controller;

import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class GeneratorServiceController {

	@Autowired
	GeneratorService generatorService;

	@PostMapping(value = "/generate")
	public void generateModules(@RequestBody @Valid TechStackRequest request, OutputStream out)
			throws IOException, InterruptedException {

		FileSystemResource resource = new FileSystemResource(generatorService.generate(request));
		try (ZipOutputStream zippedOut = new ZipOutputStream(out)) {
			ZipEntry e = new ZipEntry(resource.getFilename());
			// Configure the zip entry, the properties of the file
			e.setSize(resource.contentLength());
			e.setTime(System.currentTimeMillis());
			// etc.
			zippedOut.putNextEntry(e);
			// And the content of the resource:
			StreamUtils.copy(resource.getInputStream(), zippedOut);
			zippedOut.closeEntry();
			zippedOut.finish();
		}
		catch (Exception e) {
			// Do something with Exception
		}
	}

}
