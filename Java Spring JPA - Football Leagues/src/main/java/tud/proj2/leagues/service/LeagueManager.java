package tud.proj2.leagues.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tud.proj2.leagues.domain.League;

public interface LeagueManager extends JpaRepository<League, Integer> {
    <S extends League> S save(S league);

    League findOneByName(String name);

    League getOneByName(String name);

    void delete(League team);

    int howManyTeams(int id);

    List<League> findByStartDateBefore(Date start);
}