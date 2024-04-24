package map;

import gui.Screen;
import map.Map.Value;

public class Validator {
	private static boolean isValidMapValue(int value) {
		switch (value) {
			case Value.BOX:
			case Value.BOX_IN_WAREHOUSE:
			case Value.FIELD:
			case Value.PLAYER:
			case Value.PLAYER_IN_WALL:
			case Value.PLAYER_IN_BOX:
			case Value.PLAYER_IN_WAREHOUSE:
			case Value.WALL:
			case Value.WAREHOUSE:
				return true;
			default:
				return false;
		}
	}
	
	public static boolean isValidMapString(String map) {
		String[] rows = map.split("\n");
		
		if (rows.length != Screen.MAX_ROW)
			return false;
		
		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(",");
			
			if (cols.length != Screen.MAX_COL)
				return false;

			for (int j = 0; j < cols.length; j++) {
				int value = Integer.parseInt(cols[j]);
				
				if (!isValidMapValue(value))
					return false;
			}
		}
		
		return true;
	}
}