package tud.proj2.leagues.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tud.proj2.leagues.domain.Team;

public interface TeamManager extends JpaRepository<Team, Integer> {
    <S extends Team> S save(S team);

    Team findOneByName(String name);

    Team getOneByName(String name);

    void delete(Team team);

    @Modifying
    @Transactional
    @Query("delete from Player where team_id in (select id from Team where name=?1)")
    void deleteSquadByTeamName(String name);

    @Query("select t from Team t where t.good=1")
    List<Team> getGoodTeams();

    List<Team> findByCountryIn(Collection<String> countries);
}