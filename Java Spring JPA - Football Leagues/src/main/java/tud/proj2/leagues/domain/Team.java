package tud.proj2.leagues.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NotEmpty
    String name;
    @NotEmpty
    String country;
    @NotEmpty
    Boolean good;

    public Team(String name, String country, boolean good) {
        this.name = name;
        this.country = country;
        this.good = good;
        this.players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        player.setTeam(this);
        players.add(player);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    League league;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    List<Player> players;
}