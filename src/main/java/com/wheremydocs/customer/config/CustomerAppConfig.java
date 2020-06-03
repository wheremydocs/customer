package com.wheremydocs.customer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class CustomerAppConfig {

  @Bean
  // TODO: Verify
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper
        .getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(PRIVATE);
    return mapper;
  }

  @Bean
  // TODO: Verify
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .setDateFormat(new StdDateFormat());
  }
}
