package pl.edu.ug.tent.springmvcdemo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class ShoppingCart {
    private String id;
    private Person person;
    private List<Product> products;

    @ManyToMany
    public List<Product> getProducts() {
        return products;
    }

    @OneToOne
    public Person getPerson() {
        return person;
    }

    @Id
    @GeneratedValue
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}