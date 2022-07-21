package com.tuimm.synchronousconsumer.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .components(new Components());

  }

  @Bean
  public GroupedOpenApi groupOpenApi() {
    return GroupedOpenApi.builder().group("v1").packagesToScan("com.tuimm.synchronousconsumer.controller").build();

  }

}
