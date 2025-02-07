package gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

public interface BibConstants {

	// Fenster
	public static final Rectangle FRAME_BOUNDS = new Rectangle(400, 500, 690, 600);
	public static final String FRAME_TITLE = "Biblio-BBQ";
	public static final String TAB1_TITLE = "Personeneintrag";
	public static final String TAB2_TITLE = "Bucheintrag";

	// Standardgröße für alle BibTextField-Instanzen
	public static final Dimension DEFAULT_SIZE_TF = new Dimension(250, 25);

	// Schriftart & -größe
	public static final Font FONT_TAB = new Font("Verdana", Font.BOLD, 16);
	public static final Font FONT_HEADLINE = new Font("Verdana", Font.BOLD, 16);
	public static final Font FONT_LABEL = new Font("Verdana", Font.PLAIN, 14);
	public static final Font FONT_CONTROLS = new Font("Verdana", Font.BOLD, 14);
	public static final Font FONT_MESSAGES = new Font("Verdana", Font.PLAIN, 14);

	// Farben vom Logo
	public static final Color COLOR_RED = new Color(253, 88, 107);
	public static final Color COLOR_GREEN = new Color(126, 189, 56);
	public static final Color COLOR_ORANGE = new Color(252, 177, 94);
	public static final Color COLOR_BLUE = new Color(88, 136, 236);
	public static final Color COLOR_BRIGHT = new Color(227, 239, 243);

	// Farben
	public static final Color COLOR_TAB = new Color(77, 97, 255);
	public static final Color COLOR_LABEL = new Color(84, 84, 84);
	public static final Color COLOR_CONTROLS = new Color(84, 84, 84);
	public static final Color COLOR_CANCELBTN = new Color(250, 128, 114);
	public static final Color COLOR_SAVEBTN = new Color(175, 225, 175);
	public static final Color COLOR_BTN_TXT = new Color(0, 0, 0);
	public static final Color COLOR_BG = new Color(194, 229, 255);

	// Person Panel Labels
	public static final String LABEL_PERSON = "Person";
	public static final String LABEL_VORNAME = "Vorname";
	public static final String LABEL_NACHNAME = "Nachname";
	public static final String LABEL_GEBDAT = "Geburtsdatum";
	public static final String LABEL_GESCHLECHT = "Geschlecht";
	public static final String LABEL_ADRESSE = "Adresse";
	public static final String LABEL_PLZ = "PLZ";
	public static final String LABEL_STADT = "Stadt";
	public static final String LABEL_STRASSE = "Straße";
	public static final String LABEL_HAUSNUMMER = "Hausnummer";

	// Buch Panel Labels
	public static final String LABEL_KATEGORIE = "Kategorie";
	public static final String LABEL_GENRE = "Genre";
	public static final String LABEL_FACHBEREICH = "Fachbereich";
	public static final String LABEL_ISBN = "ISBN";
	public static final String LABEL_BUCHTITEL = "Buchtitel";
	public static final String LABEL_AUTOR = "Autor";
	public static final String LABEL_INHALT = "Inhalt";
	public static final String LABEL_ALTER = "Altersgruppe";

	// Button-Konstante
	public static final int BUTTON_CORNER_RADIUS = 15;
}
