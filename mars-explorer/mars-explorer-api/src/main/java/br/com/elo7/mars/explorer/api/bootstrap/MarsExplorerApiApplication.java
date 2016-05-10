package br.com.elo7.mars.explorer.api.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("br.com.elo7.mars.explorer")
public class MarsExplorerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarsExplorerApiApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("br.com.elo7")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/api/*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Elo7 Mars Explorer API")
				.description("Elo7 Mars Explorer API - Teste de Programa\u00E7\u00E3o Elo7")
				.contact("Pedro T. Oliveira - pedro.oliveira20@gmail.com")
				.license("Apache License Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.version("2.0")
				.build();
	}
}
