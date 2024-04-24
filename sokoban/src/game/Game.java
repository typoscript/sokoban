package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.Button;
import gui.Message;
import gui.Screen;
import gui_panel.PanelManager;

import key.KeyHandler;
import map.Map;
import player.Player;

public abstract class Game extends JPanel {
	protected final Mode MODE;
	protected final PanelManager panelManager;
	protected Map map;
	protected Player player;
	protected Message msgPanel;
	protected KeyHandler keyHandler;
	protected Status status;
	
	public boolean isQuit = false;
	
	public enum Status {
		WIN,
		GAME_OVER,
		PLAYING,
		MAP_NOT_FOUND,
		EDIT;
	}

	public enum Mode {
		PLAY,
		MAKE_MAP;
	}

	public Game(Mode mode, PanelManager panelManager) {
		this.panelManager = panelManager;
		MODE = mode;

		setWindowSetting();
		setKeyboardInputSetting();

		setFocusable(true); // focus on this panel to accept keypress input
		
		add(getQuitModeButton());
	}
	
	private JButton getQuitModeButton() {
		Button btn = new Button("뒤로가기");
		btn.setBounds(0, 0, 100, 25);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSetting();
				panelManager.runLobby();
			}
		});

		return btn;
	}
	
	private void setKeyboardInputSetting() {
		keyHandler = new KeyHandler(this);
		addKeyListener(keyHandler);	
	}
	
	private void setWindowSetting() {
		setLayout(null);
		setPreferredSize(Screen.getComponentFullSize());
		setBackground(Screen.GAME_BACKGROUND_COLOR);
	}
	
	protected void handleMapNotFound() {
		status = Status.MAP_NOT_FOUND;
		msgPanel.setGameStatus(status);
	}
	
	abstract public void paintComponent(Graphics g);
	abstract public void run();
	abstract protected void resetSetting();
	abstract protected void update();
}