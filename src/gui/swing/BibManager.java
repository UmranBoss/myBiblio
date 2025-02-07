package gui.swing;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

/**
 * Einstiegspunkt für GUI
 */
public class BibManager {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {

			// Tab-Ecken abrunden
			try {
				UIManager.setLookAndFeel(new FlatLightLaf());

				/*
				 * CustomScrollPane hinzufügen (bzgl. der Entfernung der hellgrauen Linie beim
				 * ScrollPane)
				 */
				UIManager.put("ScrollPaneUI", CustomScrollPane.class.getName());
				UIManager.put(CustomScrollPane.class.getName(), CustomScrollPane.class);

			} catch (Exception ex) {
				System.err.println("Beim FlatLaf lief etwas schief: " + ex.getMessage());
			}

			new BibFrame().setVisible(true);

		});
	}

}
