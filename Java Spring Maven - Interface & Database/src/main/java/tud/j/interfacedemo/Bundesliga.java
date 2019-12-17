package tud.j.interfacedemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Bundesliga implements League {
    public String name = "Bundesliga";
    public String country = "Germany";
    public ArrayList<LeagueTeam> leaguetable = new ArrayList<LeagueTeam>();

    public Bundesliga(@Value("#{'${buli.club.names}'.split(',')}") List<String> clubnames) {
        for (int i = 0; i < 16; i++) {
            leaguetable.add(new LeagueTeam(clubnames.get(i)));
        }
    }

    @Override
    public void replaceTeam(LeagueTeam a, LeagueTeam b) {
        leaguetable.remove(a);
        leaguetable.add(b);
        this.sort();
    }

    @Override
    public void match(LeagueTeam home, int scoreA, int scoreB, LeagueTeam away) {
        LeagueTeam a = home;
        LeagueTeam b = away;
        int result = scoreA - scoreB;
        if (result > 0) {
            a.played++;
            a.wins++;
            a.points += 3;
            a.gd += result;
            b.played++;
            b.loses++;
            b.gd -= result;
        } else if (result < 0) {
            b.played++;
            b.wins++;
            b.points += 3;
            b.gd -= result;
            a.played++;
            a.loses++;
            a.gd += result;
        } else {
            a.played++;
            a.draws++;
            a.points++;
            b.played++;
            b.draws++;
            b.points++;
        }
        this.replaceTeam(home, a);
        this.replaceTeam(away, b);
        this.sort();
    }

    @Override
    public void sort() {
        Collections.sort(leaguetable, new LeagueTeamComparator());
    }

    @Override
    public String toString() {
        String output = "";
        output += this.country + "\n";
        output += this.name + "\n";
        output += "Name\t\tPlayed\tWins\tDraws\tLoses\tGD\tPoints\n";
        for (LeagueTeam leagueTeam : leaguetable) {
            output += leagueTeam + "\n";
        }
        return output;
    }

    @Override
    public LeagueTeam findTeamByName(String name) {
        for (LeagueTeam lt : leaguetable) {
            if (lt.name.equals(name)) {
                return lt;
            }
        }
        return null;
    }

    public LeagueTeam deepcopy(Bundesliga buli1920) {
        return null;
    }
}