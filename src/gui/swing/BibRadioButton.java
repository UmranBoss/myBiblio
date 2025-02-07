package gui.swing;

import javax.swing.JRadioButton;

public class BibRadioButton extends JRadioButton implements BibConstants{

	public BibRadioButton() {
		super();
		setSelected(true);
		setForeground(COLOR_BLUE);
		setFont(FONT_CONTROLS);
		setHorizontalTextPosition(LEFT);
	
	}

	public BibRadioButton(String text) {
		super(text);
	}

}
