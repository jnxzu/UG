package tud.proj2.leagues.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tud.proj2.leagues.domain.Team;

public interface TeamManager extends JpaRepository<Team, Integer> {
    <S extends Team> S save(S team);

    Team findById(int id);

    Team getOneById(int id);

    void delete(Team team);

    List<Team> findByCountryOrCountry(String countryA, String countryB);

    @Query("select count(*) from Team")
    int howManyTeamsInDb();
}