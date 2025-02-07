package data.fake;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.*;

import data.DataManager;
import data.Entities.Adresse;
import data.Entities.Geschlecht;
import data.Entities.Person;

public class JavaFakeLibraryGen {

	static final String DELETE_ALL_GESCHLECHT = "DELETE FROM GESCHLECHT;";
	static final String RESET_AI_GESCHLECHT = "ALTER TABLE GESCHLECHT AUTO_INCREMENT = 1;";
	static final String DELETE_ALL_PERSON = "DELETE FROM PERSON;";
	static final String RESET_AI_PERSON = "ALTER TABLE PERSON AUTO_INCREMENT = 1;";
	static final String DELETE_ALL_ADRESSE = "DELETE FROM ADRESSE;";
	static final String RESET_AI_ADRESSE = "ALTER TABLE ADRESSE AUTO_INCREMENT = 1;";
	private static DataManager dm = DataManager.getInstance();

	public static void main(String[] args) {
		Faker fake = new Faker(Locale.forLanguageTag("de"));
		createFakeAdresse(fake);
		createFakePerson(fake);

		System.out.println("Fake-Daten erzeugt! Cool :-)");
	}

	public static void deleteResetAllAdresse() {
		dm.executeSQL(DELETE_ALL_ADRESSE);
		dm.executeSQL(RESET_AI_ADRESSE);
	}

	public static void deleteResetAllPerson() {
		dm.executeSQL(DELETE_ALL_PERSON);
		dm.executeSQL(RESET_AI_PERSON);
	}

	public static void deleteResetAllGeschlecht() {
		dm.executeSQL(DELETE_ALL_GESCHLECHT);
		dm.executeSQL(RESET_AI_GESCHLECHT);
	}

	public static void createFakeAdresse(Faker fake) {

		deleteResetAllAdresse();

		Adresse adr;

		for (int i = 0; i < 10; i++) {
			// JavaFakeBiblio hat PLZ nur als String, aber in meiner DB ist PLZ ein int-Typ
			String plzString = fake.address().zipCode();
			// PLZ soll aus 5 Ziffern bestehen
			String plzString5digitsFormatted = String.format("%05d", Integer.parseInt(plzString));
			// Daher wird es von String in Int umgewandelt
			int plz = Integer.parseInt(plzString5digitsFormatted);
			String stadt = fake.address().cityName();
			String strasse = fake.address().streetName();
			String hausnummer = fake.address().buildingNumber();

			adr = new Adresse(plz, stadt, strasse, hausnummer);
			adr.save();
		}
	}

	public static void createFakePerson(Faker fake) {

		deleteResetAllPerson();
		createFakeGeschlecht();
		createFakeAdresse(fake);

		ArrayList<Geschlecht> genders = dm.getAllGeschlecht();
		ArrayList<Adresse> addresses = dm.getAllAdresse();

		Person p;
		Geschlecht ges;
		Adresse add;

		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			String vorname = fake.name().firstName();
			String nachname = fake.name().lastName();
			LocalDate gebdat = generateRandomBirthDate(4, 90);
			ges = genders.get(rand.nextInt(genders.size()));
			add = addresses.get(rand.nextInt(addresses.size()));

			p = new Person(vorname, nachname, gebdat, ges, add);
			p.save();
		}
	}

	public static LocalDate generateRandomBirthDate(int minAlter, int maxAlter) {
		Random rand = new Random();

		int alter = rand.nextInt((maxAlter - minAlter) + 1) + minAlter;
		LocalDate aktuellesDat = LocalDate.now();
		int geburtsJahr = aktuellesDat.getYear() - alter;
		int monat = rand.nextInt(12) + 1;
		int maxTag = LocalDate.of(geburtsJahr, monat, 1).lengthOfMonth();
		int tag = rand.nextInt(maxTag) + 1;

		return LocalDate.of(geburtsJahr, monat, tag);
	}

	public static void createFakeGeschlecht() {

		deleteResetAllGeschlecht();
		Geschlecht ges;

		ges = new Geschlecht("w", "weiblich ");
		ges.save();
		ges = new Geschlecht("m", "maennlich");
		ges.save();
		ges = new Geschlecht("d", "divers");
		ges.save();
	}
}
