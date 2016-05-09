package br.com.elo7.mars.explorer.engine.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Spring Boot Command Line Application.
 *
 * @author pedrotoliveira
 */
@SpringBootApplication
@ComponentScan("br.com.elo7.mars.explorer.engine")
@EnableMongoRepositories("br.com.elo7.mars.explorer.engine.domain.surface")
public class MarsExplorerEngineApplication implements CommandLineRunner {
	
	@Autowired
	private SurfaceScanEngine surfaceScanEngine;

	public static void main(String[] args) {
		SpringApplication.run(MarsExplorerEngineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args != null && args.length > 0) {			
			Collection<String> inputs = new ArrayList<>();			
			Arrays.asList(args).forEach(inputs :: add);			
			Collection<String> outputs = surfaceScanEngine.createSurfaceAndScan(inputs);
			outputs.forEach(System.out :: println);
		}
	}
}
