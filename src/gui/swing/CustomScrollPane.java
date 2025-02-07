package gui.swing;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;

public class CustomScrollPane extends BasicScrollPaneUI {

	public static ComponentUI createUI(JComponent c) {
		return new CustomScrollPane();
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		JScrollPane scrollPane = (JScrollPane) c;
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
	}

	@Override
	protected void installDefaults(JScrollPane scrollpane) {
		super.installDefaults(scrollpane);
		scrollpane.setBackground(UIManager.getColor("Panel.background"));
		scrollpane.getViewport().setBackground(UIManager.getColor("Panel.background"));
	}

	protected void paintBorder(Graphics g, JComponent c, int x, int y, int width, int height) {
		// Leer lassen, um keine Border zu zeichnen!!!!!
	}

	protected JScrollPane createScrollPane() {
		return new JScrollPane() {
			@Override
			public void setViewportBorder(javax.swing.border.Border border) {
				// Setzen einer Viewport-Border verhindern
			}

			@Override
			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
	}
}
