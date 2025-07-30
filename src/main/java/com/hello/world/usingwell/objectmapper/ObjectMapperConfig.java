package com.hello.world.usingwell.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ObjectMapperConfig {

  //@Bean
  public ObjectMapper obectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    JavaTimeModule javaTimeModule = new JavaTimeModule();
    LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
    objectMapper.registerModule(javaTimeModule);

    return objectMapper;
  }

  //@Bean
  public Jackson2ObjectMapperBuilderCustomizer objectMapperCustomizer() {
    return new Jackson2ObjectMapperBuilderCustomizer() {
      @Override
      public void customize(Jackson2ObjectMapperBuilder builder) {
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        builder.serializerByType(LocalDateTime.class, localDateTimeSerializer);
      }
    };
  }

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

    return builder.serializerByType(LocalDateTime.class, localDateTimeSerializer).build();
  }

}
