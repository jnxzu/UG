package pl.edu.ug.tent.springmvcdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Person {
    @GeneratedValue
    @Id
    private int id;
    private String firstName;
    private int yob;

    public Person(String firstName, int yob) {
        this.firstName = firstName;
        this.yob = yob;
    }
}
