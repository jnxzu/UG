package pl.edu.ug.tent.springmvcdemo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Product {
    private String id;
    private String name;
    private List<ShoppingCart> carts;

    @ManyToMany(mappedBy = "products")
    public List<ShoppingCart> getCarts() {
        return carts;
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