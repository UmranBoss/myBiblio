package bl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.DataManager;

// Verarbeitet die Eingabedaten
// Kümmert sich um die Verarbeitung & Weitergabe
// der eingegebenen Daten an die DB (DTO)
public class FormularPersonManager {

	/**
	 * Speichert die Person & verwendet dabei die Adresse, die zuvor von saveAddress
	 * gespeichert wurde.
	 * 
	 * @param formularPerson
	 */
	public void savePerson(FormularPerson formularPerson) {

		try (Connection con = DataManager.getInstance().getConnection()) {
			// Adresse in die DB-Adresse-Tabelle speichern & generierte ID zurückgeben
			int addressId = saveAddress(con, formularPerson);

			// GebDat formatieren
			String gebdatString = formularPerson.getGebdat();
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date gebdat = inputFormat.parse(gebdatString);
			String formattedGebdat = outputFormat.format(gebdat);

			// Person mit der FK "adresse_id" in die DB-Person-Tabelle speichern
			String insertPersonSQL = "INSERT INTO person (vorname, nachname, gebdat, geschlecht_id, adresse_id) "
					+ "VALUES (?, ?, ?, ?, ?)";

			try (PreparedStatement pstmt = con.prepareStatement(insertPersonSQL)) {
				pstmt.setString(1, formularPerson.getVorname());
				pstmt.setString(2, formularPerson.getNachname());
				pstmt.setString(3, formattedGebdat);
				pstmt.setInt(4, formularPerson.getGeschlecht());
				pstmt.setInt(5, addressId);

				pstmt.executeUpdate();
				System.out.println(
						"Person gespeichert: " + formularPerson.getVorname() + " " + formularPerson.getNachname());
			}
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Speichert die Adresse & gibt die ID der gespeicherten Adresse zurück, die
	 * dann in savePerson verwendet wird
	 */
	private int saveAddress(Connection con, FormularPerson formularPerson) {
		String insertAddressSQL = "INSERT INTO adresse (plz, stadt, strasse, hausnummer) " + "VALUES (?, ?, ?, ?)";

		int addressId = -1; // Falls keine ID generiert wird

		try (PreparedStatement pstmt = con.prepareStatement(insertAddressSQL,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, formularPerson.getPlz());
			pstmt.setString(2, formularPerson.getStadt());
			pstmt.setString(3, formularPerson.getStrasse());
			pstmt.setString(4, formularPerson.getHausnummer());

			pstmt.executeUpdate();

			// Die generierte Adresse-ID abfragen
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					addressId = rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressId;
	}

//		System.out.println("Speichern der Person: ");
//		System.out.println(formularPerson.getVorname() + " " + formularPerson.getNachname());
//		System.out.println(formularPerson.getGebdat());
//		System.out.println(formularPerson.getGeschlecht());
//		System.out.println(formularPerson.getPlz() + " " + formularPerson.getStadt());
//		System.out.println(formularPerson.getStrasse() + " " + formularPerson.getHausnummer());
}
