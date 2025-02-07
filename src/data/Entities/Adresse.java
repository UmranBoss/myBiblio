package data.Entities;

import data.DataManager;

/*
 * Die Klasse repräsentiert die Entität Adresse (DB-Tabelle)
 * Die Felder der Klasse entsprechen den Attributen der Entität (Spalten)
 * Eine Instanz der Klasse enstspricht ein Datensatz in der DB-Tabelle
 */

public class Adresse extends DataAccessObject {

	public final String SQL_INSERT = "INSERT INTO adresse (PLZ, Stadt, Strasse, Hausnummer) VALUES('%s', '%s', '%s', '%s')";
	public final String SQL_UPDATE = "UPDATE adresse set PLZ = '%s', Stadt = '%s', Strasse = '%s', Hausnummer = '%s' WHERE id = %d;";

	private int plz;
	private String stadt;
	private String strasse;
	private String hausnummer;

	/*
	 * Konstruiert eine Instanz der Klasse Initialisiert alle Felder außer ID
	 * 
	 * @param plz
	 * 
	 * @param stadt
	 * 
	 * @param strasse
	 * 
	 * @param hausnummer
	 */

	public Adresse(int plz, String stadt, String strasse, String hausnummer) {
		super();
		this.plz = plz;
		this.stadt = stadt;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
	}
	
	

	public Adresse() {
		super();
	}



	@Override
	public String getSqlString() {
		if (id > 0) {
			return SQL_UPDATE.formatted(plz, stadt, strasse, hausnummer, id);
		} else {
			return SQL_INSERT.formatted(plz, stadt, strasse, hausnummer);
		}
	}

	public static Adresse loadById(int id) {
		return DataManager.getInstance().loadAdresseById(id);
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
}
