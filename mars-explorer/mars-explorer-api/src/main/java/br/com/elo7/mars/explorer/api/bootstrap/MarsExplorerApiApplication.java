package br.com.elo7.mars.explorer.api.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("br.com.elo7.mars.explorer")
@PropertySource("classpath:application.properties")
@EnableMongoRepositories("br.com.elo7.mars.explorer.engine.domain.surface")
public class MarsExplorerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarsExplorerApiApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
