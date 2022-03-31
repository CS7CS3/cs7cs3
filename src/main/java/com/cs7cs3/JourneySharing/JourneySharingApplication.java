package com.cs7cs3.JourneySharing;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @EnableSwagger2
public class JourneySharingApplication {
	public static void main(String[] args) {
		SpringApplication.run(JourneySharingApplication.class, args);
	}

	// @Bean
	// public Docket productApi() {
	// return new Docket(DocumentationType.SWAGGER_2).select()
	// .apis(RequestHandlerSelectors.basePackage("com.cs7cs3.JourneySharing")).build();
	// }
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().pathsToMatch("/**")
				.group("com.cs7cs3.JourneySharing")
				.build();
	}

}
