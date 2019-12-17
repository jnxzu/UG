package pl.edu.ug.tent.springmvcdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Person {
    private String id;
    private String firstName;
    private int yob;
    private Address address;
    // private List<Car> cars;

    public Person(String firstName, int yob, Address address) {
        this.firstName = firstName;
        this.yob = yob;
        this.address = address;
    }

    // @OneToMany
    // public List<Car> getCars() {
    // return cars;
    // }

    @OneToOne
    public Address getAddress() {
        return address;
    }

    @GeneratedValue
    @Id
    public String getId() {
        return id;
    }
}
