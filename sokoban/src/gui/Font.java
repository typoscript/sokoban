package gui;

import java.awt.Graphics;

public class Font {
	public static final String FAMILY = java.awt.Font.MONOSPACED;
	public static final int STYLE = java.awt.Font.BOLD;
	public static final int SIZE = 25;

	public static java.awt.Font get() {
		return new java.awt.Font(FAMILY, STYLE, SIZE);
	}

	public static int getFontWidth(Graphics g, String text) {
		return g.getFontMetrics().stringWidth(text);
	}

	public static int getFontHeight(Graphics g) {
		return g.getFontMetrics().getHeight();
	}
}