package tud.proj2.leagues.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import tud.proj2.leagues.domain.League;

@Transactional(readOnly = true)
public interface LeagueManager extends JpaRepository<League, Integer> {
    @Transactional
    <S extends League> S save(S league);

    League findOneByName(String name);

    // @Transactional
    League getOneByName(String name);

    @Transactional
    void delete(League team);

    int howManyTeams(int id);

    List<League> findByStartDateBefore(Date start);
}