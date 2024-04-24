package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.Button;
import gui.Color;
import gui.MapMakerGUI;
import gui.Message;
import gui_panel.PanelManager;

import map.FileManager;
import map.MapComboBoxItem;
import map.MapMaker;
import map.MapNameComboBox;
import map.Validator;

import player.Player;

public class ModeMapMaker extends Game {
	private boolean isEditMode = false;
	private MapNameComboBox comboBox = new MapNameComboBox();
	private MapMaker map;

	public ModeMapMaker(PanelManager panelManager) {
		super(Mode.MAKE_MAP, panelManager);
		resetSetting();
		setToMapSelectionMode();
		
		if (comboBox.isEmpty())
			msgPanel.setGameStatus(Status.MAP_NOT_FOUND);
		
		setComboBox();
		addGUIComponents();
	}
	
	private void addGUIComponents() {
		addControlButtons();
		add(comboBox);
	}

	private void addControlButtons() {
		add(getButtonMapAdd());
		add(getButtonMapDelete());
		add(getButtonMapSave());
		add(getButtonMapRename());
		add(getButtonInstruction());
	}

	private void setToMapSelectionMode() {
		isEditMode = false;
		setFocusable(false);
		keyHandler.disableKeyPressAction();
	}

	private void setToMapEditMode() {
		isEditMode = true;
		setFocusable(true);	// allow keypress
		requestFocus();		// set focus from ComboBox to this panel
		keyHandler.enableKeyPressAction();
		msgPanel.setGameStatus(Status.EDIT);
	}
	
	private boolean isValidStageLevel(String stageLevel) {
		try {
			Integer.parseInt(stageLevel);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	private void handleAfterPlayerMove() {
		int x = player.getX();
		int y = player.getY();
		
		if (map.isOutOfMap(x, y))
			player.setToPrevPosition();
		else
			map.setMapAfterPlayerMove(x, y);
	}

	private void handleBuildObject() {
		int x = player.getX();
		int y = player.getY();
		
		if (keyHandler.hasPressedBuildBoxKey())
			map.setPlayerInBoxAt(x, y);
		else if (keyHandler.hasPressedBuildWallKey())
			map.setPlayerInWallAt(x, y);
		else if (keyHandler.hasPressedBuildWarehouseKey())
			map.setPlayerInWarehouseAt(x, y);
	}

	private void setComboBox() {
		comboBox.setBounds(500, 0, 150, 25);
		comboBox.setBackground(Color.LIGHT_BROWN);
		comboBox.setForeground(Color.DARK_BROWN);
		
		// run when selection is made
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapComboBoxItem selectedItem = comboBox.getSelectedItem();

				if (!comboBox.isValidItem(selectedItem)) {
					handleMapNotFound();
					return;
				}

				resetSetting();
				
				String data = FileManager.getMapAsString(selectedItem.STAGE_LEVEL);

				// in case of item is deleted after combox setup
				if (!Validator.isValidMapString(data)) {
					int option = JOptionPane.showConfirmDialog(null, "맵 파일이 손상 되었습니다.\n파일을 삭제하시겠습니까?", "", JOptionPane.OK_CANCEL_OPTION);
					
					if (option == JOptionPane.OK_OPTION) {
						comboBox.removeItem(selectedItem);
						boolean isFileDeleted = FileManager.deleteFile(selectedItem.FILE_NAME);
						
						if (isFileDeleted)
							JOptionPane.showMessageDialog(null, "파일이 삭제되었습니다");
					}

					comboBox.selectDefaultItem();
					repaint();
					return;
				}
				
				map.loadMapFromFile(selectedItem.STAGE_LEVEL);
				player.reset(map.getPlayerInitX(), map.getPlayerInitY());
				setToMapEditMode();
				
				repaint();
			}
		});
	}
	
	private JButton getButtonInstruction() {
		Button btn = new Button("맵 만드는 법");
		btn.setBounds(650, 0, MapMakerGUI.Button.WIDTH + 50, MapMakerGUI.Button.HEIGHT);
		
		String instruction = "" +
				"w > 플레이어 위치에 벽 설치.\n" +
				"b > 플레이어 위치에 박스 설치.\n" +
				"h > 플레이어 위치에 골대 설치.\n" +
				"설치물 제거 방법 > 플레이어를 설치물에 위치하면 설치물 제거.\n" +
				"게임 플레이를 위한 플레이어의 초기 위치 설정 방법 > 맵 제작 중 저장 시, 맵의 플레이어의 위치가 게임 플레이 시, 초기 위치.\n" +
				"게임 플레이 시, 승리 처리 원리 > 게임 플레이 시 맵 클리어 후, 다음 레벨의 맵이 존재하지 않으면 게임 승리 처리.";
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, instruction);
				setToMapEditMode();
			}
		});

		return btn;	
	}

	private JButton getButtonMapAdd() {
		Button btn = new Button("맵 추가");
		btn.setBounds(100, 0, MapMakerGUI.Button.WIDTH, MapMakerGUI.Button.HEIGHT);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputStageLevel = JOptionPane.showInputDialog("새로운 맵의 레벨. 예) 1");
				
				if (!isValidStageLevel(inputStageLevel)) {
					JOptionPane.showMessageDialog(null, "잘못된 레벨입니다");
					return;
				}
				
				int stageLevel = Integer.parseInt(inputStageLevel);
				
				if (FileManager.doesMapFileExist(stageLevel)) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 파일입니다");
					return;
				}

				String fileName = FileManager.getFileName(stageLevel);
				FileManager.createFile(fileName);
				MapComboBoxItem item = new MapComboBoxItem(stageLevel, fileName);

				comboBox.addItemInNameOrder(item);
				JOptionPane.showMessageDialog(null, "맵 추가 성공");
			}
		});

		return btn;
	}

	private JButton getButtonMapDelete() {
		Button btn = new Button("삭제");
		btn.setBounds(200, 0, MapMakerGUI.Button.WIDTH, MapMakerGUI.Button.HEIGHT);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapComboBoxItem selectedItem = comboBox.getSelectedItem();
				
				if (!comboBox.isValidItem(selectedItem)) {
					JOptionPane.showMessageDialog(null, "맵 선택 후 삭제 가능합니다");
					return;
				}
				
				boolean isFileDeleted = FileManager.deleteFile(selectedItem.FILE_NAME);
				
				if (!isFileDeleted)  {
					JOptionPane.showMessageDialog(null, "맵 파일 삭제 실패");
					return;
				}
				
				JOptionPane.showMessageDialog(null, "맵 파일 삭제 성공");

				comboBox.selectDefaultItem();
				comboBox.removeItem(selectedItem);
				
				repaint(); // repaint to hide current player image
			}
		});

		return btn;
	}
	
	private JButton getButtonMapSave() {
		Button btn = new Button("저장");
		btn.setBounds(300, 0, MapMakerGUI.Button.WIDTH, MapMakerGUI.Button.HEIGHT);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapComboBoxItem selectedItem = comboBox.getSelectedItem();
				
				if (!comboBox.isValidItem(selectedItem)) {
					JOptionPane.showMessageDialog(null, "맵 선택 후 저장 가능합니다");
					return;
				}
				
				if (map.isPlayerInObject(player.getX(), player.getY())) {
					JOptionPane.showMessageDialog(null, "플레이어가 구조물에 위치할 시 맵 저장 불가");
					setToMapEditMode();
					return;
				}
				
				if (!map.hasWarehouse()) {
					JOptionPane.showMessageDialog(null, "맵에 골대가 1개 이상 존재해야합니다");
					setToMapEditMode();
					return;
				}

				if (!map.hasBox()) {
					JOptionPane.showMessageDialog(null, "맵에 박스가 1개 이상 존재해야합니다");
					setToMapEditMode();
					return;
				}

				if (!map.isNumOfWareHousesEqualsNumOfBoxes()) {
					JOptionPane.showMessageDialog(null, "골대와 박스의 숫자가 일치하지 않습니다");
					setToMapEditMode();
					return;
				}
				
				FileManager.saveToFile(map.getAsString(), map.getStageLevel());
				JOptionPane.showMessageDialog(null, "맵 파일 저장 성공");
			}
		});

		return btn;
	}

	private JButton getButtonMapRename() {
		Button btn = new Button("이름 수정");
		btn.setBounds(400, 0, MapMakerGUI.Button.WIDTH, MapMakerGUI.Button.HEIGHT);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapComboBoxItem selectedItem = comboBox.getSelectedItem();
				
				if (!comboBox.isValidItem(selectedItem)) {
					JOptionPane.showMessageDialog(null, "맵 선택 후 이름 수정 가능합니다");
					return;
				}
				
				String inputStageLevel = JOptionPane.showInputDialog("새로운 맵의 레벨 (예: 3)");
				
				if (!isValidStageLevel(inputStageLevel)) {
					JOptionPane.showMessageDialog(null, "잘못된 레벨입니다");
					return;
				}
				
				int stageLevel = Integer.parseInt(inputStageLevel);
				boolean isFileRenamed = FileManager.renameFile(selectedItem.FILE_NAME, stageLevel);
				
				if (!isFileRenamed) {
					JOptionPane.showMessageDialog(null, "이름 수정 실패");
					return;
				}
				
				String renamedFileName = FileManager.getFileName(stageLevel);
				MapComboBoxItem renamedItem = new MapComboBoxItem(stageLevel, renamedFileName);
				
				comboBox.selectDefaultItem();
				comboBox.removeItem(selectedItem);
				comboBox.addItemInNameOrder(renamedItem);

				JOptionPane.showMessageDialog(null, "파일 이름 수정 성공");

				map.setAsEmpty();
				setToMapSelectionMode();
				repaint();
			}
		});

		return btn;
	}

	@Override
	protected void handleMapNotFound() {
		super.handleMapNotFound();
		resetSetting();
		setToMapSelectionMode();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		map.draw(g);
		msgPanel.draw(g);
		
		if (!isEditMode)
			return;

		player.draw(g);
	}
	
	@Override
	protected void resetSetting() {
		status = Status.MAP_NOT_FOUND;
		msgPanel = new Message(status, MODE);
		
		map = new MapMaker();
		player = new Player(map.getPlayerInitX(), map.getPlayerInitY());
	}
	
	@Override
	public void run() {
		if (keyHandler.hasPressedBuildObjectKey()) {
			handleBuildObject();;
		}
		
		update();
		repaint();
	}
	
	@Override
	protected void update() {
		if (!keyHandler.isKeyPressed() || keyHandler.hasPressedBuildObjectKey())
			return;

		map.setMapBeforePlayerMove(player.getX(), player.getY());

		player.move(keyHandler.getPressedKey());
		
		handleAfterPlayerMove();
	}
}