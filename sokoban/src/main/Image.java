package main;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public interface Image {
	static final String CLASS_LOADER_PATH = "images/";
	static final String EXTENSION = ".png";

	public static BufferedImage getBufferedImage(String name) {
		try {
			return ImageIO.read(Image.class.getClassLoader().getResourceAsStream(CLASS_LOADER_PATH + name + Image.EXTENSION));
		} catch (Exception e) {
			return null; 
		}
	}
}