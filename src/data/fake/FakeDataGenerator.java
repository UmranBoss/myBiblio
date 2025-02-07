package data.fake;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Random;

import data.DataManager;
import data.Entities.Adresse;
import data.Entities.Geschlecht;
import data.Entities.Person;

public class FakeDataGenerator {

	static final String DELETE_ALL_GESCHLECHT = "DELETE FROM GESCHLECHT;";
	static final String RESET_AI_GESCHLECHT = "ALTER TABLE GESCHLECHT AUTO_INCREMENT = 1;";
	static final String DELETE_ALL_PERSON = "DELETE FROM PERSON;";
	static final String RESET_AI_PERSON = "ALTER TABLE PERSON AUTO_INCREMENT = 1;";
	static final String DELETE_ALL_ADRESSE = "DELETE FROM ADRESSE;";
	static final String RESET_AI_ADRESSE = "ALTER TABLE ADRESSE AUTO_INCREMENT = 1;";
	private static DataManager dm = DataManager.getInstance();

	public static void main(String[] args) {
		createFakePersons(10000);
		System.out.println("Alle Fake-Daten erfolgreich generiert! Juhuuuuuuuu!");

	}

	public static void deleteAllAdresse() {

		dm.executeSQL(DELETE_ALL_ADRESSE);
		dm.executeSQL(RESET_AI_ADRESSE);

	}

	public static void createFakeAdresse() {

		deleteAllAdresse();

		// Zufällige Daten für Adressen erstellen
		String[] staedte = { "Amsterdam", "Berlin", "Chemnitz", "Dresden", "Euskirchen" };
		String[] strassen = { "Ahrenfeld Weg", "Brombergerstrasse", "Cecilienallee", "Donaustrasse",
				"Emil-von-Bach-Weg" };

		Random rand = new Random();

		for (int i = 0; i < 10000; i++) {
			// Zufaellig generieren
			int plz = 10000 + rand.nextInt(90000); //
			String stadt = staedte[rand.nextInt(staedte.length)];
			String strasse = strassen[rand.nextInt(strassen.length)];
			String hausnummer = rand.nextInt(100) + 1 + "";

			// Neue Adresse erstellen & speichern
			Adresse adr = new Adresse(plz, stadt, strasse, hausnummer);
			adr.save();

		}
		System.out.println("Done createFakeAdresse.");

	}

	public static void deleteAllPersons() {

		dm.executeSQL(DELETE_ALL_PERSON);
		dm.executeSQL(RESET_AI_PERSON);
	}

	public static void createFakePersons(int number) {
		deleteAllPersons();
		createFakeGenders();
		createFakeAdresse();

		ArrayList<Geschlecht> genders = dm.getAllGeschlecht();
		ArrayList<Adresse> addresses = dm.getAllAdresse();
		// Zufällige Daten für Adressen erstellen
		String[] vornamen = { "Ada", "Bern", "Chantal", "Detlef", "Emil", "Maria", "Tina", "Tom", "Ali", "Umay",
				"Kristin", "Frank", "Zoe", "Wolfgang", "Volkan", "Greg", "Hans", "Ivan", "Julia", "Norbert" };
		String[] nachnamen = { "Schmidt", "Müller", "Larondelle", "Willems", "Willems", "Reiter", "Schrick" };

		Random rand = new Random();

		Person p;
		Adresse adr;
		Geschlecht g;

		for (int i = 0; i < number; i++) {

			g = genders.get(rand.nextInt(genders.size()));
			adr = addresses.get(rand.nextInt(addresses.size()));
			String vorname = vornamen[rand.nextInt(vornamen.length)];
			String nachname = nachnamen[rand.nextInt(nachnamen.length)];

			// Für zufaelliges Geburtsdatum
			int year = rand.nextInt(61) + 1940;
			int month = rand.nextInt(12) + 1;
			int day = rand.nextInt(28) + 1;
			// GebDat erstellen
			LocalDate gebdat = LocalDate.of(year, month, day);

			// Person mit zufälligen Daten erstellen
			p = new Person(vorname, nachname, gebdat, g, adr);
			p.save();
		}
		System.out.println("Done createFakePerson.");
	}

	public static void deleteAllGenders() {

		dm.executeSQL(DELETE_ALL_GESCHLECHT);
		dm.executeSQL(RESET_AI_GESCHLECHT);
	}

	public static void createFakeGenders() {

		deleteAllGenders();
		Geschlecht g;

		// Fake-Geschlechter erstellen und speichern
		g = new Geschlecht("w", "weiblich ");
		g.save();
		g = new Geschlecht("m", "maennlich");
		g.save();
		g = new Geschlecht("d", "divers");
		g.save();

		System.out.println("Done createFakeGenders.");
	}

}
