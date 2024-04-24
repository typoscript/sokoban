package game;

import java.awt.Graphics;

import gui.Color;
import gui.Font;
import gui.Message;
import gui.Screen;
import gui_panel.PanelManager;
import map.MapPlay;
import player.Player;

public class ModePlay extends Game {
	private MapPlay map;

	public ModePlay(PanelManager panelManager) {
		super(Mode.PLAY, panelManager);
		resetSetting();
	}

	protected void handleAfterPlayerMove(int playerPositionValueBeforeMove) {
		int x = player.getX();
		int y = player.getY();
		
		if (!map.canPlayerMove(x, y)) {
			player.setToPrevPosition();
			map.setMapAfterPlayerMove(player.getX(), player.getY());
		} else if (map.isBoxAt(x, y) || map.isBoxInWarehouseAt(x, y)) {
			handleBoxPush(playerPositionValueBeforeMove);
		} else {
			map.setMapAfterPlayerMove(x, y);
		}
	}

	protected void handleBoxPush(int prevMapValue) {
		int boxX = getPushedBoxX();
		int boxY = getPushedBoxY();
		
		if (!map.canPushBoxAt(boxX, boxY)) {
			player.setToPrevPosition();
			map.setValueAt(player.getX(), player.getY(), prevMapValue);
			return;
		}

		int x = player.getX();
		int y = player.getY();
		
		map.pushBoxAt(boxX, boxY);
		map.setMapAfterBoxPush(x, y);
	}

	protected void handleStageClear() {
		map.increaseStageLevel();
		map.loadMapFromFile();

		if (!map.isLoaded()) {
			handleGameWin();
			keyHandler.disableKeyPressAction();
			return;
		}

		player.reset(map.getPlayerInitX(), map.getPlayerInitY());
	}

	protected void handleGameWin() {
		status = Status.WIN;
		msgPanel.setGameStatus(status);
	}
	
	protected void handleGameOver() {
		status = Status.GAME_OVER;
		msgPanel.setGameStatus(status);
	}

	protected void handleGameRestart() {
		map.setToInitMap();
		player.reset(map.getPlayerInitX(), map.getPlayerInitY());
	}
	
	private int getPushedBoxX() {
		int x = player.getX();
		
		switch (player.getDirection()) {
			case LEFT:
				return --x;
			case RIGHT:
				return ++x;
			default:
				return x;
		}
	}

	private int getPushedBoxY() {
		int y = player.getY();

		switch (player.getDirection()) {
			case UP:
				return --y;
			case DOWN:
				return ++y;
			default:
				return y;
		}
	}

	public void drawCurrentStageLevel(Graphics g) {
		String text = "현재 레벨: " + map.getStageLevel();
		g.setFont(Font.get());
		g.setColor(Color.GRAY);

		int x = (Screen.WIDTH / 2) - (Font.getFontWidth(g, text) / 2);
		int y = (Screen.HEIGHT / 10) - (Font.getFontHeight(g) / 2);

		g.drawString(text, x, y);
	}

	@Override
	public void paintComponent(Graphics g) {
		map.draw(g);
		player.draw(g);
		msgPanel.draw(g);
		drawCurrentStageLevel(g);
	}

	@Override
	protected void resetSetting() {
		status = Status.PLAYING;
		msgPanel = new Message(status, MODE);

		map = new MapPlay();
		map.loadMapFromFile();
		player = new Player(map.getPlayerInitX(), map.getPlayerInitY());
		
		if (!map.isLoaded()) {
			handleMapNotFound();
			keyHandler.disableKeyPressAction();
		}
	}
	
	@Override
	public void run() {
		if (keyHandler.hasPressedRestartKey()) {
			handleGameRestart();
			repaint();
			return;
		}
		
		update();
		repaint();

		if (map.isStageClear()) {
			handleStageClear();
			repaint();
			return;
		}
	}

	@Override
	protected void update() {
		if (!keyHandler.isKeyPressed())
			return;

		int x = player.getX();
		int y = player.getY();

		int playerPositionValueBeforeMove = map.getValueAt(x, y);

		map.setMapBeforePlayerMove(x, y);

		player.move(keyHandler.getPressedKey());
		
		handleAfterPlayerMove(playerPositionValueBeforeMove);
	}
}
