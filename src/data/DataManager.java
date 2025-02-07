package data;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Entities.Adresse;
import data.Entities.DataAccessObject;
import data.Entities.Geschlecht;
import data.Entities.Person;

/* 
 * Gewährt den DB-Zugriff, führt alle Methoden aus für die Speicherung/Aktualisierung von Datensätzen
 */

public class DataManager implements Constants {

	public static final String URL = "jdbc:mysql://localhost:3306/myBibliothek";
	public static final String USER = "root";
	public static final String PASSWORD = "";

	private static DataManager instance; // Klassenvariable

	/*
	 * Ist nun ein Feld, wenn es lokale Variable wäre, Müsste bei jedem
	 * Methoden-Aufruf eine Verbindung hergestellt werden Dies ist sehr ineffizient
	 * & kann die Systemleistung verlangsamen
	 */
	Connection connection;

	/*
	 * Dies ist ein Konstruktor private weil Singleton
	 */
	private DataManager() {
		super();
	}

	// Fügt eine Methode hinzu, um die bestehende Verbindung zurückzugeben
	public Connection getConnection() {
		return connection;
	}

	/*
	 * Optimierung des Singleton-Datenbankmanagers: Von multiplen Connections zu
	 * einer einzigen Datenbankverbindung
	 */
	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
			// DB Connection Setup nur beim ersten Mal
			try {
				instance.connection = DriverManager.getConnection(URL, USER, PASSWORD);

				// Shutdown Hook Setup:
				Thread printingHook = new Thread(() -> {
					try {
						instance.connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				});
				Runtime.getRuntime().addShutdownHook(printingHook);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	// Methode, um alle Geschlechter abzurufen
	public ArrayList<Geschlecht> getAllGeschlecht() {

		ArrayList<Geschlecht> ges = new ArrayList<Geschlecht>();
		String sqlQuery = "select * from geschlecht;";

		// Dies stellt sicher, dass alle Ressourcen am Ende des Blocks
		// automatisch geschlossen werden, auch im Falle eines Fehlers.
		try (// Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet result = stmt.executeQuery(sqlQuery)) {

			Geschlecht g;

			while (result.next()) {
				// SQL-Abfrage aus dem ResultSet zu extrahieren und in Java-Objekte überführen
				g = new Geschlecht();
				g.setId(result.getInt("id"));
				g.setKuerzel(result.getString("kuerzel"));
				g.setInfo(result.getString("info"));

				ges.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ges;
	}

	// Methode, um alle Adressen abzurufen
	public ArrayList<Adresse> getAllAdresse() {

		ArrayList<Adresse> add = new ArrayList<>();
		String sqlQuery = "SELECT * FROM adresse";

		try (// Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet result = stmt.executeQuery(sqlQuery)) {

			Adresse a;

			while (result.next()) {

				a = new Adresse();
				a.setId(result.getInt("id"));
				a.setPlz(result.getInt("plz"));
				a.setStadt(result.getString("stadt"));
				a.setStrasse(result.getString("strasse"));
				a.setHausnummer(result.getString("hausnummer"));

				add.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return add;
	}

	// Methode, um alle Personen abzurufen
	public ArrayList<Person> getAllPerson() {

		ArrayList<Person> pers = new ArrayList<>();
//		String sqlQuery = "SELECT * FROM person";
//
//		try (// Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//				Statement stmt = connection.createStatement();
//				ResultSet result = stmt.executeQuery(sqlQuery)) {
//
//			Person p;
//
//			while (result.next()) {
//
//				p = new Person();
//				p.setId(result.getInt("id"));
//				p.setVorname(result.getString("vorname"));
//				p.setNachname(result.getString("nachname"));
//				p.setGebdat(result.getDate("gebdat").toLocalDate()); // das GebDat als java.sql.Date lesen & dann in
//																		// LocalDate umwandeln
//
//				// Lade das Geschlecht anhand der geschlecht_id
//				int geschlechtId = result.getInt("geschlecht_id");
//				p.setGeschlecht(DataManager.getInstance().loadGeschlechtById(geschlechtId));
//
//				// Lade die Adresse anhand der adresse_id
//				int adresseId = result.getInt("adresse_id");
//				p.setAdresse(DataManager.getInstance().loadAdresseById(adresseId));
//
//				pers.add(p);
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return pers;
//	}
		String sqlQuery = "SELECT vorname, nachname FROM person ORDER BY id DESC LIMIT 30\r\n" + "";

		try (Statement stmt = connection.createStatement(); ResultSet result = stmt.executeQuery(sqlQuery)) {

			while (result.next()) {
				Person p = new Person();
				p.setVorname(result.getString("vorname"));
				p.setNachname(result.getString("nachname"));
				pers.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pers;
	}

	/*
	 * Speichert ein beliebiges Datenobjekt in die DB Nimmt ein DAO als Parameter,
	 * das die zu speichernden Daten enthält
	 */

	public void save(DataAccessObject dao) {
		try {
			Statement stmt = connection.createStatement();
			String sql = dao.getSqlString(); // Holt SQL-Insert-Statement vom DAO
			// System.out.println(sql); // Gibt SQL-Befehl in der Konsole aus
			stmt.execute(sql, Statement.RETURN_GENERATED_KEYS); // Führt SQL-Befehl aus

			ResultSet result = stmt.getGeneratedKeys(); // Holt automatisch generierte ID von der DB
			if (result.next()) {
				int pk = result.getInt(1); // Holt die neue ID (PK)
				dao.setId(pk); // Setzt die ID im DAO-Objekt
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SQL-Statement wird ausgeführt
	public String executeSQL(String sqlStatement) {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return ERROR_MYSQL_CONNECTION;
		}
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return ERROR_MYSQL_CONNECTION;
		}
		try {
			stmt.execute(sqlStatement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return ERROR_MYSQL_CONNECTION;
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return ERROR_MYSQL_CONNECTION;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return ERROR_MYSQL_CONNECTION;
		}
		return null;

	}

	// Neue Methoden: Objekt anhand der gegebenen ID aus der Datenbank abrufen
	public Geschlecht loadGeschlechtById(int id) {
		Geschlecht g = null;
		String sqlQuery = "SELECT * FROM GESCHLECHT WHERE ID = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
			pstmt.setInt(1, id);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					g = new Geschlecht();
					g.setId(resultSet.getInt("id"));
					g.setKuerzel(resultSet.getString("kuerzel"));
					g.setInfo(resultSet.getString("info"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	public Adresse loadAdresseById(int id) {
		Adresse adr = null;
		String sqlQuery = "SELECT * FROM adresse WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
			pstmt.setInt(1, id);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					adr = new Adresse(resultSet.getInt("PLZ"), resultSet.getString("Stadt"),
							resultSet.getString("Strasse"), resultSet.getString("Hausnummer"));
					adr.setId(resultSet.getInt("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adr;
	}

	public Person loadPersonById(int id) {
		Person pers = null;
		String sqlQuery = "SELECT * FROM person WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
			pstmt.setInt(1, id);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					Geschlecht geschlecht = loadGeschlechtById(resultSet.getInt("geschlecht_id"));
					Adresse adresse = loadAdresseById(resultSet.getInt("adresse_id"));
					pers = new Person(resultSet.getString("vorname"), resultSet.getString("nachname"),
							resultSet.getDate("gebdat").toLocalDate(), geschlecht, adresse);
					pers.setId(resultSet.getInt("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;
	}
}
