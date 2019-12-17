package com.example.validForm;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PeopleListService {

  @Autowired
  @Qualifier("peoplelist")
  ArrayList<Person> people;

  ArrayList<Person> getPeople() {
    return people;
  }

}
