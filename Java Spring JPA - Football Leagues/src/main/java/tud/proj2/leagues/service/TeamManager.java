package tud.proj2.leagues.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import tud.proj2.leagues.domain.Team;

@Transactional(readOnly = true)
public interface TeamManager extends JpaRepository<Team, Integer> {
    @Transactional
    <S extends Team> S save(S team);

    Team findOneByName(String name);

    // @Transactional
    Team getOneByName(String name);

    @Transactional
    void delete(Team team);

    @Modifying
    @Transactional
    @Query("delete from Player where team_id in (select id from Team where name=?1)")
    void deleteSquadByTeamName(String name);

    @Query("select t from Team t where t.good=1")
    List<Team> getGoodTeams();

    List<Team> findByCountryIn(Collection<String> countries);
}