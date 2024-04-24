package player;

import java.awt.Graphics;

import gui.Pixel;
import key.Key;

public class Player extends Position {
	private WalkImage walkImage = new WalkImage();
	private Direction direction = Direction.DOWN;
	
	public Player(int x, int y) {
		setX(x);
		setY(y);
	}

	public Direction getDirection() {
		return direction;
	}
	
	public void reset(int initX, int initY) {
		setX(initX);
		setY(initY);
		setPrevPositionToCurrentPosition();
		
		direction = Direction.DOWN;
	}
	
	public void move(int key) {
		setPrevPositionToCurrentPosition();
		
		switch (key) {
			case Key.UP:
				direction = Direction.UP;
				setY(getY() - 1);
				break;
			case Key.DOWN:
				direction = Direction.DOWN;
				setY(getY() + 1);
				break;
			case Key.LEFT:
				direction = Direction.LEFT;
				setX(getX() - 1);
				break;
			case Key.RIGHT:
				direction = Direction.RIGHT;
				setX(getX() + 1);
				break;
			default:
				return;
		}
		
		walkImage.setNextMotionImage();
	}
	
	public void draw(Graphics g) {
		Pixel.drawImage(g, walkImage.get(direction), getX(), getY());
	}
}