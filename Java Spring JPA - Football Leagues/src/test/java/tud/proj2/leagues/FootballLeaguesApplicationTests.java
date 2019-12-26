package tud.proj2.leagues;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tud.proj2.leagues.domain.League;
import tud.proj2.leagues.domain.Player;
import tud.proj2.leagues.domain.Team;
import tud.proj2.leagues.service.LeagueManager;
import tud.proj2.leagues.service.PlayerManager;
import tud.proj2.leagues.service.TeamManager;

@SpringBootTest
class FootballLeaguesApplicationTests {
	@Autowired
	LeagueManager lm;
	@Autowired
	TeamManager tm;
	@Autowired
	PlayerManager pm;

	@Test
	void validManagersTest() {
		assertNotNull(lm);
		assertNotNull(tm);
		assertNotNull(pm);
	}

	@Test
	void CRUDTest() {
		// empty check
		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();
		assertTrue(lm.findAll().isEmpty());
		assertTrue(tm.findAll().isEmpty());
		assertTrue(pm.findAll().isEmpty());

		Player initialPlayer = new Player("Philippe", "Coutinho", 100.0, 27);
		Team initialTeam = new Team("Bayern", "Germany", true);
		League initialLeague = new League("Bundesliga", Date.valueOf("2019-08-15"));
		initialTeam.addPlayer(initialPlayer);
		initialLeague.addTeam(initialTeam);
		lm.save(initialLeague); // INSERT (C)

		// SELECT (R)
		League compareLeague = lm.findOneByName("Bundesliga");
		Team compareTeam = tm.findOneByName("Bayern");
		Player comparePlayer = pm.findOneByFirstNameAndLastName("Philippe", "Coutinho");
		assertTrue(initialLeague.getId() == compareLeague.getId());
		assertTrue(initialTeam.getId() == compareTeam.getId());
		assertTrue(initialPlayer.getId() == comparePlayer.getId());

		// UPDATE (U)
		compareLeague = lm.getOne(compareLeague.getId());
		compareLeague.setName("Serie A");
		lm.save(compareLeague);
		compareTeam = tm.getOne(compareTeam.getId());
		compareTeam.setName("Juventus");
		compareTeam.setCountry("Italy");
		compareTeam.setGood(false);
		tm.save(compareTeam);
		comparePlayer = pm.getOne(comparePlayer.getId());
		comparePlayer.setFirstName("Mattia");
		comparePlayer.setLastName("De Sciglio");
		comparePlayer.setValue(15.0);
		pm.save(comparePlayer);
		assertTrue(lm.findOneByName("Serie A") != null);
		assertTrue(lm.findOneByName("Bundesliga") == null);
		assertTrue(tm.findOneByName("Juventus") != null);
		assertTrue(tm.findOneByName("Bayern") == null);
		assertTrue(pm.findOneByFirstNameAndLastName("Mattia", "De Sciglio") != null);
		assertTrue(pm.findOneByFirstNameAndLastName("Philippe", "Coutinho") == null);

		// DELETE (D)
		lm.delete(compareLeague);
		tm.delete(compareTeam);
		pm.delete(comparePlayer);
		assertTrue(lm.findAll().isEmpty());
		assertTrue(tm.findAll().isEmpty());
		assertTrue(pm.findAll().isEmpty());
	}

	@Test
	void LeagueCustomMethodsAndQueriesTest() {
		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();

		League testLeague = new League("La Liga", Date.valueOf("2019-08-15"));
		Team testTeamOne = new Team("FC Barcelona", "Spain", false);
		Team testTeamTwo = new Team("Real Madrid", "Spain", true);
		testLeague.addTeam(testTeamOne);
		testLeague.addTeam(testTeamTwo);
		lm.save(testLeague);
		League lateLeague = new League("League", Date.valueOf("2019-11-11"));
		lm.save(lateLeague);

		// COUNT named query param
		int result = lm.howManyTeams(testLeague.getId());
		assertTrue(result == 2);

		// query method BEFORE
		List<League> beforeLeagues = lm.findByStartDateBefore(Date.valueOf("2019-10-10"));
		assertTrue(beforeLeagues.size() == 1);

		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();
	}

	@Test
	void TeamCustomMethodsAndQueriesTest() {
		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();

		Team badTeam = new Team("Tottenham", "England", false);
		tm.save(badTeam);
		Team otherTeam = new Team("Lechia Gdansk", "Poland", false);
		tm.save(otherTeam);
		Team testTeam = new Team("Chelsea", "England", true);
		Player testPlayerOne = new Player("Tammy", "Abraham", 65.0, 22);
		Player testPlayerTwo = new Player("Mason", "Mount", 40.0, 20);
		Player testPlayerThree = new Player("Eden", "Hazard", 150.0, 28);
		pm.save(testPlayerThree);
		testTeam.addPlayer(testPlayerOne);
		testTeam.addPlayer(testPlayerTwo);
		tm.save(testTeam);

		// DELETE query param
		List<Player> justToCompare = pm.findAll();
		tm.deleteSquadByTeamName("Chelsea");
		List<Player> queryResult = pm.findAll();
		assertTrue(queryResult.size() == justToCompare.size() - 2);

		// SELECT query
		List<Team> goodTeams = tm.getGoodTeams();
		assertTrue(goodTeams.size() == 1);

		// query method IN
		ArrayList<String> checkCountries = new ArrayList<String>(Arrays.asList("England", "Poland"));
		List<Team> clubsInCountries = tm.findByCountryIn(checkCountries);
		assertTrue(clubsInCountries.size() == 3);

		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();
	}

	@Test
	void PlayerCustomMethodsAndQueriesTest() {
		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();

		Player testPlayerOne = new Player("Leo", "Messi", 200.0, 32);
		Player testPlayerTwo = new Player("Cristiano", "Ronaldo", 190.0, 35);
		Player testPlayerThree = new Player(null, "Neymar", 195.0, 27);
		pm.save(testPlayerOne);
		pm.save(testPlayerTwo);
		pm.save(testPlayerThree);

		// SELECT named query
		List<Player> all = pm.listAllInDb();
		assertTrue(all.size() == 3);

		// query method GREATER
		List<Player> expensive = pm.findByValueGreaterThan(190.0);
		assertTrue(expensive.size() == 2);

		// DELETE query method AND
		pm.deleteByFirstNameAndLastName("Leo", "Messi");
		List<Player> postCut = pm.findAll();
		assertTrue(postCut.size() == 2);

		lm.deleteAll();
		tm.deleteAll();
		pm.deleteAll();
	}
}
