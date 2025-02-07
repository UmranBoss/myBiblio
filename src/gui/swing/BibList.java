package gui.swing;

import java.awt.Color;

import javax.swing.JList;

public class BibList extends JList<String> implements BibConstants {

	public BibList() {
		super();
		setBackground(Color.WHITE);
		setForeground(COLOR_BLUE);
		setFont(FONT_CONTROLS);
	}

}
