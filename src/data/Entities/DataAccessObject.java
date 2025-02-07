package data.Entities;

import data.DataManager;

/**
 * Mutterklasse aller Entitätsklassen
 */
public abstract class DataAccessObject {

	protected int id;

	public abstract String getSqlString();

	public void save() {
		DataManager.getInstance().save(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}