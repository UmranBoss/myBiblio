package gui.swing;

import java.awt.Dimension;

import javax.swing.JTextField;

public class BibTextField extends JTextField implements BibConstants {

	public BibTextField() {
		super();
		setPreferredSize(DEFAULT_SIZE_TF);
	}

	/*
	 * Konstruktor mit Text: ruft den Standardkonstruktor auf und setzt den
	 * übergebenen Text
	 */
	public BibTextField(String text) {
		this(); // ruft den Standardkonstruktor auf & erstellt ein leeres TF
		setText(text); // setzt den übergebenen Text in das TF
	}

}
