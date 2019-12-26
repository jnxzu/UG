package tud.proj2.leagues.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "League.howManyTeams", query = "select count(t) from Team t where league_id=:id")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotEmpty
    @Column(unique = true)
    String name;
    @NotEmpty
    Date startDate;

    public League(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
        this.teams = new ArrayList<Team>();
    }

    public void addTeam(Team team) {
        team.setLeague(this);
        teams.add(team);
    }

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    List<Team> teams;
}