package gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class BibButton extends JButton implements BibConstants {

	public BibButton() {
		super();
		setForeground(COLOR_BLUE);
		setBackground(Color.WHITE);
		setFont(FONT_CONTROLS);
		setPreferredSize(new Dimension(150, 25));
		setButtonCorner(this, BUTTON_CORNER_RADIUS);
	}

	/*
	 * Button transparent machen (nur Rand & Text sichtbar), HG des Buttons
	 * entfernt, Design flexibler gestalten, damit Farbe in der
	 * paintComponent-Methode bei Interaktionen angepasst werden kann
	 */
	private void setButtonCorner(BibButton btn, int btnRadus) {
		btn.setFocusPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(getBackground().GRAY);
		} else if (getModel().isRollover()) {
			g.setColor(getBackground().darker());
		} else {
			g.setColor(getBackground());
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), BUTTON_CORNER_RADIUS, BUTTON_CORNER_RADIUS);

		super.paintComponent(g);
	}

}
