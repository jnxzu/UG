package tud.j.interfacedemo;

interface League {
    public void replaceTeam(LeagueTeam a, LeagueTeam b);

    public void match(LeagueTeam a, int scoreA, int scoreB, LeagueTeam b);

    public void sort();

    public LeagueTeam findTeamByName(String name);
}