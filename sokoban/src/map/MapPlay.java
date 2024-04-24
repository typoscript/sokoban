package map;

public class MapPlay extends Map {
	private int numOfWarehousesToClear = 0;
	private boolean isLoaded = false;
	
	private static class InitValue {
		private static int numOfWarehousesToClear;
	}

	public void increaseStageLevel() {
		stageLevel++;
	}

	public void increaseNumOfWarehousesToClear() {
		numOfWarehousesToClear++;
	}

	public void decreaseNumOfWarehousesToClear() {
		numOfWarehousesToClear--;
	}

	public boolean canPushBoxAt(int x, int y) {
		return !isOutOfMap(x, y) && (isFieldAt(x, y) || isWarehouseAt(x, y));
	}

	public boolean canPlayerMove(int x, int y) {
		return !isOutOfMap(x, y) && !isPlayerAtWall(x, y);
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public boolean isStageClear() {
		return numOfWarehousesToClear == 0;
	}

	public boolean isFieldAt(int x, int y) {
		return map[y][x] == Value.FIELD;
	}

	public boolean isPlayerAtWall(int x, int y) {
		return map[y][x] == Value.WALL;
	}

	public boolean isBoxInWarehouseAt(int x, int y) {
		return map[y][x] == Value.BOX_IN_WAREHOUSE;
	}

	public int getValueAt(int x, int y) {
		return map[y][x];
	}

	public void setBoxInWarehouseAt(int x, int y) {
		map[y][x] = Value.BOX_IN_WAREHOUSE;
	}

	public void setValueAt(int x, int y, int value) {
		map[y][x] = value;
	}

	public void setToInitMap() {
		numOfWarehousesToClear = InitValue.numOfWarehousesToClear;

		for (int i = 0; i < Map.InitValue.map.length; i++)
			map[i] = Map.InitValue.map[i].clone();
	}

	public void setMapAfterBoxPush(int playerX, int playerY) {
		int x = playerX;
		int y = playerY;

		if (isBoxAt(x, y)) {
			setPlayerAt(x, y);
		} else if (isBoxInWarehouseAt(x, y)) {
			setPlayerInWarehouseAt(x, y);
			increaseNumOfWarehousesToClear();
		}
	}

	public void pushBoxAt(int x, int y) {
		if (isWarehouseAt(x, y)) {
			setBoxInWarehouseAt(x, y);
			decreaseNumOfWarehousesToClear();
			return;
		}

		setBoxAt(x, y);
	}

	public void loadMapFromFile() {
		String mapData = FileManager.getMapAsString(stageLevel);

		if (!Validator.isValidMapString(mapData)) {
			isLoaded = false;
			return;
		}

		String[] rows = mapData.split("\n");
		
		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(",");

			for (int j = 0; j < cols.length; j++) {
				int value = Integer.parseInt(cols[j]);
				
				if (value == Value.PLAYER) {
					Map.InitValue.playerX = j;
					Map.InitValue.playerY = i;
				} else if (value == Value.WAREHOUSE)
					numOfWarehousesToClear++;

				map[i][j] = value;
			}
			
			Map.InitValue.map[i] = map[i].clone();
		}
		
		InitValue.numOfWarehousesToClear = numOfWarehousesToClear;
		isLoaded = true;
	}

	@Override
	public void loadMapFromFile(int stageLevel) {
		this.stageLevel = stageLevel;
		loadMapFromFile();
	}

	@Override
	public void setMapBeforePlayerMove(int playerX, int playerY) {
		int x = playerX;
		int y = playerY;
		
		if (isPlayerInWarehouseAt(x, y))
			setWarehouseAt(x, y);
		else
			setFieldAt(x, y);
	}

	@Override
	public void setMapAfterPlayerMove(int playerX, int playerY) {
		int x = playerX;
		int y = playerY;

		if (isWarehouseAt(x, y))
			setPlayerInWarehouseAt(x, y);
		else
			setPlayerAt(x, y);
	}
}