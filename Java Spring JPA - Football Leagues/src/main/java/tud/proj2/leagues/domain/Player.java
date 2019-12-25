package tud.proj2.leagues.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String firstName;
    @NotEmpty
    String lastName;
    @NotEmpty
    Float value;
    @Min(value = 1)
    @Max(value = 99)
    int shirtNum;
    @NotEmpty
    int age;

    @ManyToOne
    Team team;
}