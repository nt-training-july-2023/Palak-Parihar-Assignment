package com.grievance.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for CORS (Cross-Origin Resource Sharing).
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

  /**
   *
   */
  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedOrigins("http://localhost:3000")
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowedHeaders(
        "Origin",
        "Content-Type",
        "Accept",
        "Authorization",
        "email",
        "password"
      )
      .exposedHeaders("Access-Control-Allow-Origin");
  }
}
