package player;

import java.awt.image.BufferedImage;

import main.Image;

public class WalkImage {
	private final BufferedImage UP_1;
	private final BufferedImage UP_2;
	private final BufferedImage DOWN_1;
	private final BufferedImage DOWN_2;
	private final BufferedImage LEFT_1;
	private final BufferedImage LEFT_2;
	private final BufferedImage RIGHT_1;
	private final BufferedImage RIGHT_2;

	private int walkMotionNum = 1;
	
	public WalkImage() {
		UP_1 = Image.getBufferedImage("man_walk_up_1");
		UP_2 = Image.getBufferedImage("man_walk_up_2");

		DOWN_1 = Image.getBufferedImage("man_walk_down_1");
		DOWN_2 = Image.getBufferedImage("man_walk_down_2");
		
		LEFT_1 = Image.getBufferedImage("man_walk_left_1");
		LEFT_2 = Image.getBufferedImage("man_walk_left_2");

		RIGHT_1 = Image.getBufferedImage("man_walk_right_1");
		RIGHT_2 = Image.getBufferedImage("man_walk_right_2");
	}
	
	public void setNextMotionImage() {
		walkMotionNum = walkMotionNum > 0 ? 0 : ++walkMotionNum;
	}

	public BufferedImage get(Direction direction) {
		switch (direction) {
			case UP:
				return walkMotionNum == 1 ? UP_1 : UP_2;
			case DOWN:
				return walkMotionNum == 1 ? DOWN_1 : DOWN_2;
			case LEFT:
				return walkMotionNum == 1 ? LEFT_1 : LEFT_2;
			case RIGHT:
				return walkMotionNum == 1 ? RIGHT_1 : RIGHT_2;
		}
		
		return null;
	}
}