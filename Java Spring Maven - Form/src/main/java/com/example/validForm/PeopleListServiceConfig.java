package com.example.validForm;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PeopleListServiceConfig {

  @Bean
  public ArrayList<Person> peoplelist() {
    return new ArrayList<Person>();
  }

}
