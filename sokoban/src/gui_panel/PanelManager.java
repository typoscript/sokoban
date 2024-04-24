package gui_panel;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Game;
import game.ModeMapMaker;
import game.ModePlay;
import game.Game.Mode;

public class PanelManager extends JFrame {
	Lobby lobby;
	Game game;
	
	public PanelManager() {
		lobby = new Lobby(this);
		addPanel(lobby);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); // center window
	}
	
	private void addPanel(JPanel panel) {
		add(panel);
		pack();

		setVisible(true);
	}
	
	private void removePanel(JPanel panel) {
		panel.setVisible(false);
		panel = null;
	}
	
	public void runGame(Mode mode) {
		if (mode == Mode.PLAY)
			game = new ModePlay(this);
		else if (mode == Mode.MAKE_MAP)
			game = new ModeMapMaker(this);

		addPanel(game);

		removePanel(lobby);
		game.setVisible(true);
		game.requestFocus();
	}
	
	public void runLobby() {
		if (game != null)
			removePanel(game);
		
		if (lobby == null) {
			lobby = new Lobby(this);
			addPanel(lobby);
		}

		lobby.setVisible(true);
	}
}
