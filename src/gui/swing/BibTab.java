package gui.swing;

import javax.swing.JTabbedPane;

public class BibTab extends JTabbedPane  implements BibConstants{

	public BibTab() {
		super(JTabbedPane.TOP);
		setFont(FONT_TAB);
		setForeground(COLOR_BLUE);
	}

}
