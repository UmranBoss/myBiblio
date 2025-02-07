package gui.swing;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import bl.FormularPerson;
import bl.FormularPersonManager;
import data.DataManager;
import data.Entities.Geschlecht;
import data.Entities.Person;

public class BibPanelFirst extends BibPanel implements BibConstants {

	BibTextField vornameTF = new BibTextField();
	BibTextField nachnameTF = new BibTextField();
	BibTextField gebdatTF = new BibTextField();

	BibRadioButton wRB = new BibRadioButton("weiblich");
	BibRadioButton mRB = new BibRadioButton("männlich");
	BibRadioButton dRB = new BibRadioButton("divers");

	BibTextField plzTF = new BibTextField();
	BibTextField stadtTF = new BibTextField();
	BibTextField strTF = new BibTextField();
	BibTextField hausnrTF = new BibTextField();

	BibButton saveBTN = new BibButton();
	BibButton cancelBTN = new BibButton();

	private JList<String> dbList;
	private DefaultListModel<String> listModel;

	// Füge diese Felder in Deiner Klasse BibPanelFirst hinzu:
	private Person selectedPerson = null;
	private List<Person> displayedPersons = new ArrayList<>();

	/*
	 * Alle Sub-Panels erstellen & anordnen, um das Layout zu initialisieren
	 */
	public BibPanelFirst() {
		super();
		addDateValidationListener(gebdatTF);
		initDataGender();
		initComponents(); // Initialisierung auslagern
	}

	private void initComponents() {
		// Haupt-Panel
		setLayout(new BorderLayout());

		// Sub-Panels
		JPanel personPanel = createPersonPanel();
		JPanel addressPanel = createAddressPanel();
		JPanel buttonPanel = createButtonPanel();
		JPanel listPanel = createListPanel();

		// Alle Sub-Panels ausser listPanel nochmal zu einem linken Panel hinzufügen
		BibPanel leftPanel = new BibPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(personPanel);
		leftPanel.add(addressPanel);
		leftPanel.add(buttonPanel);

		// Scrollbar Panel rechts für die Liste
		JScrollPane listScrollPane = new JScrollPane(listPanel);
		listScrollPane.setPreferredSize(new Dimension(200, 0));

		// Panels zum Haupt-Panel hinzufügen
		add(leftPanel, BorderLayout.CENTER);
		add(listScrollPane, BorderLayout.EAST);

		// Liste initialisieren
		listModel = new DefaultListModel<>();
	}

	private void initDataGender() {
		ArrayList<Geschlecht> geschlechter = DataManager.getInstance().getAllGeschlecht();
		if (geschlechter.size() >= 3) {
			wRB.setText(geschlechter.get(0).getInfo());
			mRB.setText(geschlechter.get(1).getInfo());
			dRB.setText(geschlechter.get(2).getInfo());
		} else {
			wRB.setText("Weiblich");
			mRB.setText("Männlich");
			dRB.setText("Divers");
		}
	}

	// Sub-Panel 1
	private JPanel createPersonPanel() {
		BibPanel panel = new BibPanel();
		panel.setLayout(new java.awt.GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Persönliche Angaben");
		panel.setBorder(titledBorder);

		ButtonGroup gGroup = new ButtonGroup();
		gGroup.add(wRB);
		gGroup.add(mRB);
		gGroup.add(dRB);
		wRB.setSelected(true);

		BibPanel gPanel = new BibPanel();
		gPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		gPanel.add(wRB);
		gPanel.add(mRB);
		gPanel.add(dRB);

		initDataGender();

		addPlaceholderTextListener(vornameTF, LABEL_VORNAME);
		addPlaceholderTextListener(nachnameTF, LABEL_NACHNAME);
		addPlaceholderTextListener(gebdatTF, "TT.MM.JJJJ");

		// Abstände zw. Labels & Textfelder
		gbc.insets = new java.awt.Insets(5, 5, 5, 5);

		// Komponenten mit GridBagLayout hinzufügen
		gbc.gridx = 0; // Spalte
		gbc.gridy = 0; // Zeile
		gbc.anchor = GridBagConstraints.LINE_START; // für alle Labels linksbündig
		panel.add(new JLabel(LABEL_VORNAME), gbc);

		gbc.gridx = 1;
		panel.add(vornameTF, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel(LABEL_NACHNAME), gbc);

		gbc.gridx = 1;
		panel.add(nachnameTF, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel(LABEL_GEBDAT), gbc);

		gbc.gridx = 1;
		panel.add(gebdatTF, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel(LABEL_GESCHLECHT), gbc);

		gbc.gridx = 1;
		panel.add(gPanel, gbc);

		return panel;
	}

	// Sub-Panel 2
	private JPanel createAddressPanel() {
		BibPanel panel = new BibPanel();
		panel.setLayout(new java.awt.GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Adressangaben");
		panel.setBorder(titledBorder);

		addPlaceholderTextListener(plzTF, LABEL_PLZ);
		addPlaceholderTextListener(stadtTF, LABEL_STADT);
		addPlaceholderTextListener(strTF, LABEL_STRASSE);
		addPlaceholderTextListener(hausnrTF, LABEL_HAUSNUMMER);

		gbc.insets = new java.awt.Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		panel.add(new JLabel("PLZ"), gbc);

		gbc.gridx = 1;
		panel.add(plzTF, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Stadt"), gbc);

		gbc.gridx = 1;
		panel.add(stadtTF, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Straße"), gbc);

		gbc.gridx = 1;
		panel.add(strTF, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Hausnummer"), gbc);

		gbc.gridx = 1;
		panel.add(hausnrTF, gbc);

		return panel;
	}

	// Sub-Panel 3
	private JPanel createButtonPanel() {
		BibPanel panel = new BibPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		cancelBTN.setText("Löschen");
		saveBTN.setText("Speichern");
		panel.add(cancelBTN);
		panel.add(saveBTN);

		saveBTN.addActionListener(e -> saveData());
		cancelBTN.addActionListener(e -> cancelData());

		return panel;
	}

	private int getSelectedGenderId() {
		ArrayList<Geschlecht> geschlechter = DataManager.getInstance().getAllGeschlecht();
		if (wRB.isSelected()) {
			System.out.println("Test für W" + geschlechter.get(0).getId());
			return geschlechter.get(0).getId();
		} else if (mRB.isSelected()) {
			System.out.println("Test für M" + geschlechter.get(1).getId());
			return geschlechter.get(1).getId();
		} else {
			System.out.println("Test für D" + geschlechter.get(2).getId());
			return geschlechter.get(2).getId();
		}
	}

	private void saveData() {
		// Check, ob alle Felder befüllt sind
		if (!completedFields()) {
			JOptionPane.showMessageDialog(this, "Bitte füllen Sie alle erforderlichen Felder aus.", "Fehler",
					JOptionPane.ERROR_MESSAGE);
			return; // Abbrechen, wenn Felder leer
		}

		int choice = JOptionPane.showConfirmDialog(this, "Sind alle Eingaben vollständig?", "Bestätigung",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (choice == JOptionPane.YES_OPTION) {
			// Daten aus den Eingabefeldern holen
			String vorname = vornameTF.getText().trim();
			String nachname = nachnameTF.getText().trim();
			String gebdat = gebdatTF.getText().trim();
			int geschlechtId = getSelectedGenderId();
			String plz = plzTF.getText().trim();
			String stadt = stadtTF.getText().trim();
			String strasse = strTF.getText().trim();
			String hausnummer = hausnrTF.getText().trim();

			// Datum formatieren (von "dd.MM.yyyy" nach "yyyy-MM-dd")
			String formattedGebdat = formatDate(gebdat);
			System.out.println("Formatted Gebdat: " + formattedGebdat);

			// FormularPerson instanziieren & Felder setzen
			FormularPerson formularPerson = new FormularPerson(vorname, nachname, gebdat, geschlechtId, plz, stadt,
					strasse, hausnummer);

			// ManagerFormularPerson zum Speichern der Daten aufrufen
			FormularPersonManager formularPersonManager = new FormularPersonManager();
			formularPersonManager.savePerson(formularPerson);

			JOptionPane.showMessageDialog(this, "Die Eingaben wurden erfolgreich gespeichert.", "Hurra!",
					JOptionPane.INFORMATION_MESSAGE);

			resetFields(); // nach Speicherung
		} else {
			JOptionPane.showMessageDialog(this, "Bitte überprüfen Sie Ihre Eingaben erneut.", "Check",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Methode zur Datumsformats-Umwanldung
	private String formatDate(String gebdat) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = inputFormat.parse(gebdat);
			return outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ungültiges Geburtsdatum!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return null; // Falls das Datum nicht geparst werden kann
		}
	}

	// Methode für den Check
	public boolean completedFields() {
		return !vornameTF.getText().trim().equals(LABEL_VORNAME) && !nachnameTF.getText().trim().equals(LABEL_NACHNAME)
				&& !gebdatTF.getText().trim().equals(LABEL_GEBDAT) && !plzTF.getText().trim().equals(LABEL_PLZ)
				&& !stadtTF.getText().trim().equals(LABEL_STADT) && !strTF.getText().trim().equals(LABEL_STRASSE)
				&& !hausnrTF.getText().trim().equals(LABEL_HAUSNUMMER);
	}

	// Methode zur Geschlechtsabfrage
	private String getSelectedGender() {
		if (wRB.isSelected()) {
			return "weiblich";
		} else if (mRB.isSelected()) {
			return "männlich";
		} else {
			return "divers";
		}
	}

	// Eingabefelder zurücksetzen, nachdem die Daten erfolgreich gespeichert wurden
	private void resetFields() {
		vornameTF.setText("");
		nachnameTF.setText("");
		gebdatTF.setText("");
		plzTF.setText("");
		stadtTF.setText("");
		strTF.setText("");
		hausnrTF.setText("");
		wRB.setSelected(true);
	}

	private void cancelData() {
		int choice = JOptionPane.showConfirmDialog(this, "Möchtest Du wirklich abbrechen?", "Abbruch",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (choice == JOptionPane.YES_OPTION) {
			resetFields();
			JOptionPane.showMessageDialog(this, "Vorgang abgebrochen. Alle Eingaben wurden gelöscht.",
					"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Setze deine Angaben fort.", "Zurück zum Formular",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void loadPersonDataToList() {
		// Check, ob das Modell korrekt initialisiert wurde
		if (listModel == null) {
			listModel = new DefaultListModel<>();
		}

		// Modell der JList setzen
		dbList.setModel(listModel);
		listModel.clear();
		List<Person> personData = DataManager.getInstance().getAllPerson();

//		for (Person person : personData) {
//			String fullName = person.getVorname() + " " + person.getNachname();
//			listModel.addElement(fullName);
		int limit = Math.min(30, personData.size());
		for (int i = limit - 1; i >= 0; i--) {
			Person person = personData.get(i);
			String fullName = person.getVorname() + " " + person.getNachname();
			listModel.addElement(fullName);
		}
	}

	// Sub-Panel 4
	private JPanel createListPanel() {
		BibPanel panel = new BibPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(border, "Aktuelle Liste");
		panel.setBorder(titledBorder);

		listModel = new DefaultListModel<>();
		dbList = new JList<>(listModel);
		dbList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// Vor- & Nachname in die Liste laden
		loadPersonDataToList();

		// Hier später die richtige Logik implementieren
//		List<String> exampleData = new ArrayList<>();
//		exampleData.add("Max Mustermann");
//		exampleData.add("Erika Musterfrau");
//
//		for (String person : exampleData) {
//			listModel.addElement(person);
//		}

		// Standard-JScrollPane erstellen
		// Benutzerdefinierte UI setzen
		// Damit die Entfernung der hellgrauen Linie greift
		JScrollPane scrollPane = new JScrollPane(dbList);
		scrollPane.setUI(new CustomScrollPane());
		scrollPane.setPreferredSize(new Dimension(150, 200));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		dbList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				// Person aus der Liste holen
				String selectedPerson = dbList.getSelectedValue();
				loadPersonDetails(selectedPerson);
			}
		});

		panel.add(scrollPane);

		return panel;
	}

	private void loadPersonDetails(String selectedPerson) {
		// TODO Auto-generated method stub

	}

	private void addPlaceholderTextListener(BibTextField textField, String placeholder) {
		textField.setForeground(Color.GRAY);
		textField.setText(placeholder);

		textField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(Color.GRAY);
					textField.setText(placeholder);
				}
			}
		});
	}

	private void addDateValidationListener(BibTextField textField) {
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String dateText = textField.getText();
				if (!isValidDate(dateText)) {
					textField.setForeground(Color.RED);
					textField.setToolTipText("Ungültiges Datum! Bitte im Format TT.MM.JJJJ eingeben.");
				} else {
					textField.setForeground(Color.BLACK); // Setzt die Farbe zurück, wenn das Datum gültig ist
					textField.setTransferHandler(null);
				}
			}
		});
	}

	// Hilfsmethode zur Datumsvalidierung
	private boolean isValidDate(String date) {
		try {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false); // Verhindert, dass ungültige Daten akzeptiert werden
			sdf.parse(date);
			return true;
		} catch (java.text.ParseException e) {
			return false;
		}
	}
}
