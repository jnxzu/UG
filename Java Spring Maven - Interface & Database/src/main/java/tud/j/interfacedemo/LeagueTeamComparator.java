package tud.j.interfacedemo;

import java.util.Comparator;

public class LeagueTeamComparator implements Comparator<LeagueTeam> {
    public int compare(LeagueTeam o1, LeagueTeam o2) {
        int val1 = Integer.compare(o1.points, o2.points);
        if (val1 == 0) {
            int val2 = Integer.compare(o1.gd, o2.gd);
            return -val2;
        } else {
            return -val1;
        }
    }
}