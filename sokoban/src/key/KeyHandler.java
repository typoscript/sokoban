package key;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import game.Game;

public class KeyHandler implements KeyListener {
	private boolean isDisabled = false;
	private int pressedKey;
	private Timer timer;
	
	public KeyHandler(Game game) {
		pressedKey = Key.NONE;

		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg) {	
				if (pressedKey != Key.NONE && !isDisabled) {
					game.run();
					pressedKey = Key.NONE;
				}
			}
		});

		timer.start();
	}
	
	public void disableKeyPressAction() {
		isDisabled = true;
	}

	public void enableKeyPressAction() {
		isDisabled = false;
	}
	
	public int getPressedKey() {
		return pressedKey;
	}
	
	public boolean isKeyPressed() {
		return pressedKey == Key.NONE ? false : true;
	}
	
	public boolean hasPressedBuildWarehouseKey() {
		return pressedKey == Key.BUILD_WAREHOUSE;
	}
	
	public boolean hasPressedBuildWallKey() {
		return pressedKey == Key.BUILD_WALL;
	}
	
	public boolean hasPressedBuildBoxKey() {
		return pressedKey == Key.BUILD_BOX;
	}

	public boolean hasPressedRestartKey() {
		return pressedKey == Key.RESTART;
	}

	public boolean hasPressedBuildObjectKey() {
		return hasPressedBuildBoxKey() || hasPressedBuildWallKey() || hasPressedBuildWarehouseKey();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		pressedKey = e.getKeyCode();
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
}