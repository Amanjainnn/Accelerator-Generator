package com.hackerearth.accelerator.generator.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@Data
@NoArgsConstructor
public class Metadata {

	List<String> type;

	List<TechStackConfig> frameworks;

	List<String> backends;

	private static Metadata instance;

	private Metadata(String filename) throws IOException {
		Metadata metadata = new ObjectMapper().readValue(new ClassPathResource(filename).getFile(), Metadata.class);
		this.frameworks = metadata.frameworks;
		this.type = metadata.type;
		this.backends = metadata.backends;
	}

	public static Metadata getInstance(String filename) throws IOException {
		if (instance == null)
			instance = new Metadata(filename);

		return instance;
	}

}
