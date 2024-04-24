package player;

public abstract class Position {
	private int x, y;
	private int prevX, prevY;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setToPrevPosition() {
		setX(prevX);
		setY(prevY);
	}
	
	public void setPrevPositionToCurrentPosition() {
		prevX = x;
		prevY = y;
	}
}