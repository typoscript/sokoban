package map;

import java.util.List;

import javax.swing.JComboBox;

public class MapNameComboBox extends JComboBox<MapComboBoxItem> {
	private final MapComboBoxItem DEFAULT_ITEM = new MapComboBoxItem(-1, "맵을 고르시오");

	public MapNameComboBox() {
		addItem(DEFAULT_ITEM);
		addFileNamesToCombox();
	}
	
	public void selectDefaultItem() {
		setSelectedItem(DEFAULT_ITEM);
	}
	
	public boolean isEmpty() {
		return getItemCount() == 0;
	}
	
	public boolean isValidItem(MapComboBoxItem item) {
		if (item == null || item == DEFAULT_ITEM)
			return false;
		
		return true;
	}

	private void addFileNamesToCombox() {
		List<String> fileNames = FileManager.getFileNamesInMapFolder();
		
		for (String fileName : fileNames) {
			if (!FileManager.isValidMapFileName(fileName))
				continue;

			int stageLevel = FileManager.getStageLevelFromFileName(fileName);
			MapComboBoxItem item = new MapComboBoxItem(stageLevel, fileName);

			addItemInNameOrder(item);
		}
	}
	
	public void addItemInNameOrder(MapComboBoxItem element) {
		for (int i = 0; i < getItemCount(); i++) {
			MapComboBoxItem currentElement = getItemAt(i);

			if (element.STAGE_LEVEL < currentElement.STAGE_LEVEL) {
				insertItemAt(element, i);
				return;
			}
		}
		
		addItem(element);
	}
	
	public MapComboBoxItem getSelectedItem() {
		return (MapComboBoxItem) super.getSelectedItem();
	}
}
