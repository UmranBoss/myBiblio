package data.tests;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

import data.DataManager;
import data.Entities.Person;

public class TestNamesAscending {

	public static void main(String[] args) {
		ArrayList<Person> pers = DataManager.getInstance().getAllPerson(); // Aus der DB laden & in ArrayListe speichern

		// Nach Vorname sortieren, dann Nachname bei Gleichheit
		Collections.sort(pers, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				// Zuerst nach Nachname, wenn Nachname gleich dann nach Vorname
				int vornameVergleich = p1.getVorname().compareToIgnoreCase(p2.getVorname());
				if (vornameVergleich == 0) {
					return p1.getNachname().compareToIgnoreCase(p2.getNachname());
				}
				return vornameVergleich;
			}
		});

		// Ausgabe der sortierten Liste
		for (Person person : pers) {
			System.out.println(person.getVorname() + "\t" + person.getNachname());
		}
	}
}
