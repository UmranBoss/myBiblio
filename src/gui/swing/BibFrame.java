package gui.swing;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BibFrame extends JFrame implements BibConstants {

	public BibFrame() {
		super(FRAME_TITLE);

		ImageIcon frameIcon = new ImageIcon("src/gui/swing/img/books.png");
		setIconImage(frameIcon.getImage());

		setBounds(FRAME_BOUNDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);

		BibTab tabPanel = new BibTab();

		BibPanelFirst tab1 = new BibPanelFirst();
		tabPanel.addTab(TAB1_TITLE, tab1);
		BibPanelSecond tab2 = new BibPanelSecond();
		tabPanel.addTab(TAB2_TITLE, tab2);
		add(tabPanel);
	}

}
