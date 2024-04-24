package map;

import java.awt.image.BufferedImage;

import main.Image;
import map.Map.Value;

public class ObjectImage {
	public final BufferedImage FLOOR;
	public final BufferedImage WALL;
	public final BufferedImage WAREHOUSE;
	public final BufferedImage BOX;
	public final BufferedImage BOX_IN_WAREHOUSE;
	
	public ObjectImage() {
		BOX 				= Image.getBufferedImage("box");
		BOX_IN_WAREHOUSE	= Image.getBufferedImage("box_in_warehouse");
		FLOOR 				= Image.getBufferedImage("floor");
		WALL 				= Image.getBufferedImage("wall");
		WAREHOUSE 			= Image.getBufferedImage("warehouse");
	}

	public BufferedImage getImageByMapValue(int value) {
		switch (value) {
			case Value.BOX_IN_WAREHOUSE:
				return BOX_IN_WAREHOUSE;
			
			case Value.BOX:
			case Value.PLAYER_IN_BOX:
				return BOX;

			case Value.WALL:
			case Value.PLAYER_IN_WALL:
				return WALL;

			case Value.WAREHOUSE:
			case Value.PLAYER_IN_WAREHOUSE:
				return WAREHOUSE;

			default:
				return FLOOR;
		}
	}
}