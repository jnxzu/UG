package tud.proj2.leagues.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotEmpty
    @Column(unique = true)
    String name;
    @NotEmpty
    Date startDate;
    @NotEmpty
    Date endDate;

    @OneToMany(mappedBy = "league")
    List<Team> teams;
}