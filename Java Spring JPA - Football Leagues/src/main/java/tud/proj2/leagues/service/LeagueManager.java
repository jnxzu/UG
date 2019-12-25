package tud.proj2.leagues.service;

import org.springframework.data.jpa.repository.JpaRepository;

import tud.proj2.leagues.domain.League;

public interface LeagueManager extends JpaRepository<League, Integer> {
    <S extends League> S save(S team);

    League findById(int id);

    League getOneById(int id);

    void delete(League team);
}