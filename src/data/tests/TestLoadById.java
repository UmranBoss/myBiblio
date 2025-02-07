package data.tests;

import data.Entities.Adresse;

import data.Entities.Person;

public class TestLoadById {
	public static void main(String[] args) {

		int personId = 3;
		Person pers = Person.loadById(personId);

		if (pers != null) {
			System.out.println("Person gefunden --- Oleyyy!");
			System.out.println("ID: " + pers.getId());
			System.out.println("Vorname: " + pers.getVorname());
			System.out.println("Nachname: " + pers.getNachname());
			System.out.println("Geburtsdatum: " + pers.getGebdat());
			System.out.println("Geschlecht: " + pers.getGeschlecht().getInfo());
			System.out.println("Adresse: " + pers.getAdresse().getStadt());
		} else {
			System.out.println("Keine Person mit dieser ID gefunden.");
		}

		int adresseId = 5;
		Adresse adr = Adresse.loadById(adresseId);

		if (adr != null) {
			System.out.println("Adresse gefunden --- Coool!");
			System.out.println("Stadt: " + adr.getStadt());
			System.out.println("Strasse: " + adr.getStrasse());
		} else {
			System.out.println("Zu dieser ID gibt es keine Adresse - sorry!");
		}
	}
}
