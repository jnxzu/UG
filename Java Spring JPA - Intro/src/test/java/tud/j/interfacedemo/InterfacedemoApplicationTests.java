package tud.j.interfacedemo;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InterfacedemoApplicationTests {
	@Autowired
	Bundesliga buli1920;

	@Test
	void shouldCreateClassCorrectly() {
		assertNotNull(buli1920);
		// System.out.println(buli1920);
	}

	@Test
	void shouldChangeTeam() {
		LeagueTeam copy = buli1920.deepcopy(buli1920);
		buli1920.replaceTeam(buli1920.findTeamByName("BCS"), new LeagueTeam("BLF"));
		assertNotEquals(copy, buli1920);
		// System.out.println(buli1920);
	}

	@Test
	void shouldUpdateTableAfterMatch() {
		LeagueTeam copy = buli1920.deepcopy(buli1920);
		buli1920.match(buli1920.findTeamByName("BAY"), 2, 4, buli1920.findTeamByName("DOR"));
		assertNotEquals(copy, buli1920);
		// System.out.println(buli1920);
	}

}
