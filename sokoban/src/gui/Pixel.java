package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pixel {
	public static final int SIZE = 50;

	public static void drawImage(Graphics g, BufferedImage image, int x, int y) {
		g.drawImage(image, x * SIZE, y * SIZE, SIZE, SIZE, null);
	}
}