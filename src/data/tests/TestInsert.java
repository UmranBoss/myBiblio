package data.tests;

import data.DataManager;
import data.Entities.Adresse;
import data.Entities.Geschlecht;

public class TestInsert {

	static final String DELETE_ALL_GESCHLECHT = "DELETE FROM GESCHLECHT;";
	static final String RESET_AI_GESCHLECHT = "ALTER TABLE GENDER AUTO_INCREMENT = 1;";

	public static void main(String[] args) {

//		testFehler();
		testGender();
		testGenderUpdate();
//		testSaveAdresse();
	}

	private static void testFehler() {

		DataManager dm = DataManager.getInstance();
		String err = dm.executeSQL("hoho");
		System.out.print(err);
		char[] sqlStatement;
		System.out.println((DataManager.getInstance()).executeSQL("hihi"));
	}

	/*
	 * testet SQL-Update
	 */
	private static void testGenderUpdate() {

		Geschlecht g = new Geschlecht("Test", "Test for Update");
		g.save();
		System.out.println("vor dem Save: " + g.getInfo());
		g.setInfo("Gesaved");
		g.save();
		System.out.println("nach dem Save: " + g.getInfo());
	}

	private static void testGender() {
		DataManager dm = DataManager.getInstance();

		// Alle Datensätze löschen
		dm.executeSQL(DELETE_ALL_GESCHLECHT);
		dm.executeSQL(RESET_AI_GESCHLECHT);

		Geschlecht g;
		// Datensätze inserten
		for (int i = 1; i <= 5; i++) {
			g = new Geschlecht("G " + i, "Gender " + i);
			System.out.println("Save Gender with ID " + g.getId());
			dm.save(g);
			System.out.println("Save Gender got ID " + g.getId());
		}

//		oder optimierter schreiben
//		for (int i = 2; i <= 10; i++) {
//			dm.saveGender(new Gender("G " + i, "Gender " + i));
//		}

		System.out.println("done");
	}
}
