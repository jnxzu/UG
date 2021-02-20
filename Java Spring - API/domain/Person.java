package pl.edu.ug.tent.springmvcdemo.domain;

import lombok.Data;

@Data
public class Person {
  private String name;
  private int yob;

  public Person(String name, int yob) {
    this.name = name;
    this.yob = yob;
  }

  public Person() {
  }
}
