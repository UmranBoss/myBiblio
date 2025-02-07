package bl;

// DTO-Klasse für Person 
// Speichert die im GUI eingegebenen Daten aus dem PersonPanel
// Enthält Felder für Werte wie Vorname, Nachname und Adresse, 
// die als Datenobjekt an die DAO weitergegeben werden können.
public class FormularPerson {

	private String vorname;
	private String nachname;
	private String gebdat;
	private int geschlecht;
	private String plz;
	private String stadt;
	private String strasse;
	private String hausnummer;

	public FormularPerson(String vorname, String nachname, String gebdat, int geschlecht, String plz, String stadt,
			String strasse, String hausnummer) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.gebdat = gebdat;
		this.geschlecht = geschlecht;
		this.plz = plz;
		this.stadt = stadt;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
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

	public String getGebdat() {
		return gebdat;
	}

	public void setGebdat(String gebdat) {
		this.gebdat = gebdat;
	}

	public int getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(int geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
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
