package map;

import gui.Screen;

public class MapMaker extends Map {
	private int numOfWarehouses = 0;
	private int numOfBoxes = 0;

	public boolean hasWarehouse() {
		return numOfWarehouses > 0;
	}

	public boolean hasBox() {
		return numOfBoxes > 0;
	}

	public boolean isNumOfWareHousesEqualsNumOfBoxes() {
		return numOfWarehouses == numOfBoxes;
	}

	public boolean isPlayerInBoxAt(int x, int y) {
		return map[y][x] == Value.PLAYER_IN_BOX;
	}

	public boolean isPlayerInWallAt(int x, int y) {
		return map[y][x] == Value.PLAYER_IN_WALL;
	}

	public boolean isPlayerInObject(int x, int y) {
		return isPlayerInBoxAt(x, y) || isPlayerInWallAt(x, y) || isPlayerInWarehouseAt(x, y);
	}

	public void setWallAt(int x, int y) {
		map[y][x] = Value.WALL;
	}

	public void setPlayerInBoxAt(int x, int y) {
		map[y][x] = Value.PLAYER_IN_BOX;
	}

	public void setPlayerInWallAt(int x, int y) {
		map[y][x] = Value.PLAYER_IN_WALL;
	}

	public void setAsEmpty() {
		map = new int[Screen.MAX_ROW][Screen.MAX_COL];
	}

	public String getAsString() {
		String mapStr = "";
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				mapStr += map[i][j];
				
				if (j < map[i].length - 1)
					mapStr += ",";
			}
			mapStr += "\n";
		}

		return mapStr;
	}
	
	public static String getMakerMapAsString() {
		String map = "";
		int playerX = 0;
		int playerY = 0;
		
		for (int i = 0; i < Screen.MAX_ROW; i++) {
			for (int j = 0; j < Screen.MAX_COL; j++) {
				if (playerX == j && playerY == i)
					map += Value.PLAYER;
				else
					map += Value.FIELD;
				
				if (j < Screen.MAX_COL - 1)
					map += ",";
			}
			
			if (i < Screen.MAX_ROW - 1)
				map += "\n";
		}
		
		return map;
	}

	@Override
	public void loadMapFromFile(int stageLevel) {
		this.stageLevel = stageLevel;
		String mapData = FileManager.getMapAsString(stageLevel);

		String[] rows = mapData.split("\n");
		
		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(",");

			for (int j = 0; j < cols.length; j++) {
				int value = Integer.parseInt(cols[j]);
				
				if (value == Value.PLAYER) {
					InitValue.playerX = j;
					InitValue.playerY = i;
				} else if (value == Value.WAREHOUSE) {
					numOfWarehouses++;
				} else if (value == Value.BOX) {
					numOfBoxes++;
				}

				map[i][j] = value;
			}
			
			InitValue.map[i] = map[i].clone();
		}
	}

	@Override
	public void setMapBeforePlayerMove(int playerX, int playerY) {
		int x = playerX;
		int y = playerY;
		
		if (isPlayerInWarehouseAt(x, y)) {
			setWarehouseAt(x, y);
			numOfWarehouses++;
		} else if (isPlayerInBoxAt(x, y)) {
			setBoxAt(x, y);
			numOfBoxes++;
		} else if (isPlayerInWallAt(x, y)) {
			setWallAt(x, y);
		} else {
			setFieldAt(x, y);
		}
	}

	@Override
	public void setMapAfterPlayerMove(int playerX, int playerY) {
		int x = playerX;
		int y = playerY;

		if (isBoxAt(x, y))
			numOfBoxes--;
		else if (isWarehouseAt(x, y))
			numOfWarehouses--;

		setPlayerAt(playerX, playerY);
	}	
}