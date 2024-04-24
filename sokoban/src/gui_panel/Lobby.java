package gui_panel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Game.Mode;
import gui.Button;
import gui.Color;
import gui.Font;
import gui.Screen;
import main.Image;
import map.ObjectImage;

public class Lobby extends JPanel {
	private PanelManager panelManager;
	private final String INSTRUCTION = "방향키 > 플레이어 이동\nSpace > 게임 초기화";

	public Lobby(PanelManager panelManager) {
		this.panelManager = panelManager;

		setPreferredSize(Screen.getComponentFullSize());
		setBackground(Screen.MAIN_BACKGROUND_COLOR);
		setLayout(null);
		
		addButtonsToPanel();
	}
	
	public void paintComponent(Graphics g) {
		BufferedImage image = Image.getBufferedImage("lobby");
		BufferedImage imagePlayer = Image.getBufferedImage("man_walk_down_1");
		ObjectImage objectImage = new ObjectImage();
		final int OBJECT_SIZE = 50;

		g.drawImage(image, 0,0, Screen.WIDTH,Screen.HEIGHT, null);

		g.drawImage(objectImage.BOX, 100, 300, OBJECT_SIZE, OBJECT_SIZE, null);
		g.drawImage(objectImage.BOX, 200, 500, OBJECT_SIZE, OBJECT_SIZE, null);

		g.drawImage(imagePlayer, 200, 300, OBJECT_SIZE, OBJECT_SIZE, null);

		g.drawImage(objectImage.WAREHOUSE, 650, 300, OBJECT_SIZE, OBJECT_SIZE, null);
		g.drawImage(objectImage.WAREHOUSE, 650, 350, OBJECT_SIZE, OBJECT_SIZE, null);

		g.drawImage(objectImage.WALL, 700, 300, OBJECT_SIZE, OBJECT_SIZE, null);
		g.drawImage(objectImage.WALL, 700, 350, OBJECT_SIZE, OBJECT_SIZE, null);
	}
	
	private void setPosition(JButton btn, int x, int y) {
		btn.setBounds(x - btn.getWidth() / 2, y, btn.getWidth(), btn.getHeight());
	}
	
	private void addButtonsToPanel() {
		JButton btnGameStart = getButton("게임 시작");
		JButton btnInstruction = getButton("게임 플레이 방법");
		JButton btnMakeMap = getButton("맵 만들기");

		final int MID_X = Screen.WIDTH / 2;

		setPosition(btnGameStart, MID_X, 250);
		setPosition(btnInstruction, MID_X, 400);
		setPosition(btnMakeMap, MID_X, 550);

		add(btnGameStart);
		add(btnInstruction);
		add(btnMakeMap);
		
		btnGameStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelManager.runGame(Mode.PLAY);
			}
		});

		btnInstruction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, INSTRUCTION);
			}
		});

		btnMakeMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelManager.runGame(Mode.MAKE_MAP);
			}
		});
	}
	
	private JButton getButton(String text) {
		int width = 200;
		int height = 100;
		Button btn = new Button(text);
		
		btn.setBounds(0, 0, width, height);
		btn.setFont(new java.awt.Font(Font.FAMILY, Font.STYLE, 15));
		btn.setBackground(Color.LIGHT_BROWN);
		btn.setForeground(Color.DARK_BROWN);
		
		return btn;
	}
}
