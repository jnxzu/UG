package tud.proj2.leagues.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "Player.listAllInDb", query = "select p from Player p")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String firstName;
    @NotEmpty
    String lastName;
    @NotEmpty
    Double value;
    @NotEmpty
    int age;

    public Player(String firstName, String lastName, double value, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.value = value;
        this.age = age;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    Team team;
}