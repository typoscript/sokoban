package gui;

import java.awt.Color;
import java.awt.Dimension;

public class Screen {
	private Screen() { }

	public static final int MAX_COL = 16;
	public static final int MAX_ROW = 16;
	
	public static final int WIDTH = Pixel.SIZE * MAX_COL;
	public static final int HEIGHT = Pixel.SIZE * MAX_ROW;
	
	public static final Color GAME_BACKGROUND_COLOR = Color.BLACK;
	
	public static final Color MAIN_BACKGROUND_COLOR = Color.white;
	
	public static Dimension getComponentFullSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
}