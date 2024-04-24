package map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gui.Pixel;
import gui.Screen;

public abstract class Map {
	protected int stageLevel = 1;
	protected int[][] map = new int[Screen.MAX_ROW][Screen.MAX_COL];
	private ObjectImage objectImage = new ObjectImage();

	protected static class InitValue {
		protected static int playerX;
		protected static int playerY;
		protected static int[][] map;
	}
	
	public static class Value {
		protected static final int FIELD 				= 0;
		protected static final int WALL 				= 1;
		protected static final int PLAYER 				= 2;
		protected static final int WAREHOUSE 			= 3;
		protected static final int BOX 					= 4;
		protected static final int BOX_IN_WAREHOUSE 	= 5;
		protected static final int PLAYER_IN_WAREHOUSE	= 6;
		protected static final int PLAYER_IN_WALL 		= 7;
		protected static final int PLAYER_IN_BOX		= 8;
	}

	public Map() {
		InitValue.map = new int[Screen.MAX_ROW][Screen.MAX_COL];
	}

	public int getStageLevel() {
		return this.stageLevel;
	}

	public int getPlayerInitX() {
		return InitValue.playerX;
	}

	public int getPlayerInitY() {
		return InitValue.playerY;
	}
	
	public void setBoxAt(int x, int y) {
		map[y][x] = Value.BOX;
	}

	public void setFieldAt(int x, int y) {
		map[y][x] = Value.FIELD;
	}
	
	public void setPlayerAt(int x, int y) {
		map[y][x] = Value.PLAYER;
	}

	public void setPlayerInWarehouseAt(int x, int y) {
		map[y][x] = Value.PLAYER_IN_WAREHOUSE;
	}

	public void setWarehouseAt(int x, int y) {
		map[y][x] = Value.WAREHOUSE;
	}
	
	public boolean isBoxAt(int x, int y) {
		return map[y][x] == Value.BOX;
	}
	
	public boolean isPlayerInWarehouseAt(int x, int y) {
		return map[y][x] == Value.PLAYER_IN_WAREHOUSE;
	}
	
	public boolean isWarehouseAt(int x, int y) {
		return map[y][x] == Value.WAREHOUSE;
	}

	public boolean isOutOfMap(int x, int y) {
		return x < 0 || x >= map[0].length || y < 0 || y >= map.length;
	}

	public void draw(Graphics g) {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				BufferedImage image = objectImage.getImageByMapValue(map[y][x]);
				Pixel.drawImage(g, image, x, y);
			}
		}
	}
	
	abstract public void loadMapFromFile(int stageLevel);
	abstract public void setMapAfterPlayerMove(int playerX, int playerY);
	abstract public void setMapBeforePlayerMove(int playerX, int playerY);
}