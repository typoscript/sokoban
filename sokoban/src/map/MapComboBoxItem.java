package map;

public class MapComboBoxItem {
	public final int STAGE_LEVEL;
	public final String FILE_NAME;

	public MapComboBoxItem(int stageLevel, String fileName) {
		this.STAGE_LEVEL = stageLevel;
		this.FILE_NAME = fileName;
	}
	
	@Override
	public String toString() {
		return FILE_NAME;
	}
}