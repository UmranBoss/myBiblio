package data.Entities;

import data.DataManager;

/**
 * Beschreibt die Tabelle Geschlecht
 */

public class Geschlecht extends DataAccessObject {

	private final String SQL_INSERT = "INSERT INTO GESCHLECHT (KUERZEL, INFO) VALUES('%s', '%s')";
	private final String SQL_UPDATE = "UPDATE GESCHLECHT SET KUERZEL = '%s', info = '%s' WHERE id = %d;";
	private String kuerzel;
	private String info;

	public Geschlecht(String kuerzel, String info) {
		super();
		this.kuerzel = kuerzel;
		this.info = info;
	}

	public Geschlecht() {
		super();
	}

	// Wird in der Methode saveGender in DataManager verwendet
	@Override
	public String getSqlString() {
		if (id > 0) {
			return SQL_UPDATE.formatted(kuerzel, info, id);
		} else {
			return SQL_INSERT.formatted(kuerzel, info);
		}
	}

	@Override
	public String toString() {
		return "Geschlecht{kuerzel='" + kuerzel + "', info='" + info + "'}";
	}

	public static Geschlecht loadById(int id) {
		return DataManager.getInstance().loadGeschlechtById(id);
	}

	// Ab hier nur getter setter
	public String getKuerzel() {
		return kuerzel;
	}

	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
