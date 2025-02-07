package data.Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import data.DataManager;

/*
 * Repräsentiert die Entität Person 
 */

/**
 * 
 */
public class Person extends DataAccessObject {

	private final String SQL_INSERT = "INSERT INTO Person (vorname, nachname, gebdat, geschlecht_id, adresse_id) VALUES ('%s', '%s', '%s', '%s', '%s')";
	private final String SQL_UPDATE = "UPDATE Person set vorname = '%s', nachname = '%s', gebdat = '%s', geschlecht_id = '%s', adresse_id = '%s' WHERE id = %d;";

	private String vorname;
	private String nachname;
	private LocalDate gebdat;
	private Geschlecht geschlecht;
	private Adresse adresse;

	/*
	 * Konstruiert eine Instanz der Klasse und Initialiisert die Felder name,
	 * vorname und gebdat Die Felder geschlecht und adresse bleiben null
	 * 
	 * @param vorname
	 * 
	 * @param nachname
	 * 
	 * @param gebdat
	 */
	public Person(String vorname, String nachname, LocalDate gebdat) {
		this(vorname, nachname, gebdat, null, null);
	}

	public Person(String vorname, String nachname, LocalDate gebdat, Geschlecht geschlecht, Adresse adresse) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.gebdat = gebdat;
		this.geschlecht = geschlecht;
		this.adresse = adresse;
	}

	public Person() {
		super();
	}

	public Person(String vorname) {
		this(vorname, "", null); // Setzt den Nachnamen auf "" und Geburtsdatum auf null (oder ein anderes
									// sinnvolles Standarddatum)
	}

	@Override
	public String getSqlString() {
		// Wenn die ID > 0 ist, dann handelt es sich um ein Update
		if (id > 0) {
			return SQL_UPDATE.formatted(vorname, nachname, gebdat, geschlecht.getId(), adresse.getId(), id);
		} else {
			// Ansonsten ein Insert (wobei die ID nicht gesetzt wird, weil sie von der DB
			// automatisch vergeben wird)
			return SQL_INSERT.formatted(vorname, nachname, gebdat, geschlecht.getId(), adresse.getId(), id);
		}

	}

	public static Person loadById(int id) {
		return DataManager.getInstance().loadPersonById(id);
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public LocalDate getGebdat() {
		return gebdat;
	}

	public void setGebdat(LocalDate gebdat) {
		this.gebdat = gebdat;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/*
	 * Hierdurch wird festgelegt, wie eine Person-Instanz in der JList angezeigt
	 * wird
	 */
	@Override
	public String toString() {
		return vorname + " " + nachname;
	}

}
