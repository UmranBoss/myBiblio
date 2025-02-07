package tests.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DataManager;
import data.Entities.Geschlecht;

class TestGeschlecht {

	final String DELETE_ALL_GESCHLECHT = "DELETE FROM GESCHLECHT;";
	final String RESET_AI_GESCHLECHT = "ALTER TABLE GESCHLECHT AUTO_INCREMENT = 1;";

	@Test
	void test() {
		DataManager dm = DataManager.getInstance();

		// Alle Datensätze löschen
		dm.executeSQL(DELETE_ALL_GESCHLECHT);
		dm.executeSQL(RESET_AI_GESCHLECHT); // PK beginnt wieder bei 1

		Geschlecht g = new Geschlecht("G1", "Geschlecht 1");
		assertEquals(0, g.getId());

		g.save();
		assertEquals(1, g.getId());
	}

	@Test
	void test1() {
		Geschlecht g = new Geschlecht("G2", "Geschlecht 2");
		g.save();
		assertEquals(2, g.getId());
	}

}
