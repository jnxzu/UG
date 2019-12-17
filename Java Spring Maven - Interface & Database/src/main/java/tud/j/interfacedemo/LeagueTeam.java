package tud.j.interfacedemo;

class LeagueTeam {
    public String name;
    public int played = 0;
    public int wins = 0;
    public int draws = 0;
    public int loses = 0;
    public int gd = 0;
    public int points = 0;

    public LeagueTeam(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "\t\t" + played + "\t" + wins + "\t" + draws + "\t" + loses + "\t" + gd + "\t" + points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getGd() {
        return gd;
    }

    public void setGd(int gd) {
        this.gd = gd;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}