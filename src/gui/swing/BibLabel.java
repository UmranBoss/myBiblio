package gui.swing;

import javax.swing.JLabel;

public class BibLabel extends JLabel implements BibConstants {

	public BibLabel(String text) {
		super(text);
		setForeground(COLOR_BLUE);
		setFont(FONT_LABEL);
		setHorizontalTextPosition(LEFT);
	}
}
