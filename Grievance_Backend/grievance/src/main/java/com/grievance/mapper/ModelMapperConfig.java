package com.grievance.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for creating and configuring a ModelMapper bean.
 */
@Configuration
public class ModelMapperConfig {

  /**
   * Creates and configures a ModelMapper bean.
   *
   * @return A ModelMapper instance for object mapping operations.
   */
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

}
