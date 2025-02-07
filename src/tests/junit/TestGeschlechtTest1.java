package tests.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.DataManager;
import data.Entities.Geschlecht;

class TestGeschlechtTest1 {

	static final String DELETE_ALL_GESCHLECHT = "DELETE FROM GESCHLECHT;";
	static final String RESET_AI_GESCHLECHT = "ALTER TABLE GESCHLECHT AUTO_INCREMENT = 1;";

	@BeforeEach
	void setUp() throws Exception {
		DataManager dm = DataManager.getInstance();

		// Alle Datensätze löschen
		dm.executeSQL(DELETE_ALL_GESCHLECHT);
		dm.executeSQL(RESET_AI_GESCHLECHT); // PK beginnt wieder bei 1
	}

	@Test
	void test() {
		Geschlecht g = new Geschlecht("TsU1", "TestsetUp 1");
		assertEquals(0, g.getId());

		g.save();
		assertEquals(1, g.getId());
	}

}
