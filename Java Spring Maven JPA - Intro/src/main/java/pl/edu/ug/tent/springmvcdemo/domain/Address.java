package pl.edu.ug.tent.springmvcdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {
    private String id;
    private String address;

    @GeneratedValue
    @Id
    public String getId() {
        return id;
    }
}