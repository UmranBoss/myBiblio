package tests.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import data.DataManager;
import data.Entities.Geschlecht;

class TestLoadAllGeschlecht {

	static final String DELETE_ALL_GESCHLECHT = "DELETE FROM GESCHLECHT;";
	static final String RESET_AI_GESCHLECHT = "ALTER TABLE GESCHLECHT AUTO_INCREMENT = 1;";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DataManager dm = DataManager.getInstance();

		// Alle Datensätze löschen
		dm.executeSQL(DELETE_ALL_GESCHLECHT);
		dm.executeSQL(RESET_AI_GESCHLECHT); // PK beginnt wieder bei 1
	}

	@Test
	void test() {
		DataManager dm = DataManager.getInstance();
		ArrayList<Geschlecht> result = dm.getAllGeschlecht();
		assertEquals(0, result.size());

		Geschlecht g = new Geschlecht("GG", "info");
		g.save();
		result = dm.getAllGeschlecht();

		assertEquals(1, result.size());
	}

	@Test
	void test1() {
		DataManager dm = DataManager.getInstance();
		ArrayList<Geschlecht> result = dm.getAllGeschlecht();

		Geschlecht g = new Geschlecht("GG", "info");
		g.save();
		result = dm.getAllGeschlecht();

		assertEquals(2, result.size());

	}
}
