package gui.swing;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BibPanel extends JPanel implements BibConstants {

	public BibPanel() {
		super();
		setLayout(new BorderLayout()); // Standard-Layout für alle Children-Panels
		setBackground(COLOR_BRIGHT);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	}

}
