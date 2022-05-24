package com.hackerearth.accelerator.generator.service.impl;

import com.hackerearth.accelerator.generator.dto.Metadata;
import com.hackerearth.accelerator.generator.dto.TechStackRequest;
import com.hackerearth.accelerator.generator.exception.BadRequestException;
import com.hackerearth.accelerator.generator.service.GeneratorService;
import com.hackerearth.accelerator.generator.validator.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {

	@Value("${generator.path}")
	String path;

	@Value("${metadata.filename}")
	String filename;

	@Override
	public Path generate(TechStackRequest request) throws BadRequestException, IOException, InterruptedException {
		RequestValidator.validateRequest(request, Metadata.getInstance(filename));

		String appName = request.getAppName() + UUID.randomUUID();
		Path basePath = Path.of(path, appName, request.getAppName());
		Path zipPath = Path.of(path, appName, request.getAppName() + ".zip");
		basePath.toAbsolutePath().toFile().mkdirs();
		ModuleGeneratorFactory.generate(basePath, request, request.getBackend());
		ModuleGeneratorFactory.generate(basePath, request, request.getFrontend());
		try (FileOutputStream fos = new FileOutputStream(zipPath.toFile());
				ZipOutputStream zipOut = new ZipOutputStream(fos);) {

			zipFile(basePath.toFile().getAbsoluteFile(), basePath.toFile().getAbsoluteFile().getName(), zipOut);
		}
		return zipPath.toAbsolutePath();

	}

	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			}
			else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		try (FileInputStream fis = new FileInputStream(fileToZip)) {
			ZipEntry zipEntry = new ZipEntry(fileName);
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
		}
	}

}
