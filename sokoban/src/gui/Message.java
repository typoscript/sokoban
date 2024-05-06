package gui;

import java.awt.Graphics;
import java.awt.Font;

import game.Game.Mode;
import game.Game.Status;

public class Message {
	private final int MID_X, MID_Y;
	private final Font font;
	private final Mode MODE;
	
	private Status gameStatus;
	
	private static class Text {
		static final String GAME_WIN = "게임 승리";
		static final String GAME_OVER = "게임 종료";
		static final String MODE_MAKE_MAP = "맵 제작모드";
		static final String MODE_MAKE_MAP_NO_MAP = "로드된 맵이 없습니다. (맵을 선택하시오)";
		static final String MAP_NOT_FOUND = "맵 불러오기 실패";
	}
	
	public void setGameStatus(Status status) {
		this.gameStatus = status;
	}
	
	public Message(Status gameStatus, Mode mode) {
		this.MID_X = Screen.WIDTH / 2;
		this.MID_Y = Screen.HEIGHT / 2;
		this.MODE = mode;
		this.gameStatus = gameStatus;
		
		font = gui.Font.get();
	}
	
	private void drawMessageAtCenter(Graphics g, String text, java.awt.Color color) {
		g.setFont(font);
		g.setColor(color);

		int width = gui.Font.getFontWidth(g, text);
		int height = gui.Font.getFontHeight((g));

		int x = MID_X - (width / 2);
		int y = MID_Y - (height / 2);
		
		g.drawString(text, x, y);
	}

	public void draw(Graphics g) {
		if (MODE == Mode.MAKE_MAP) {
			if (gameStatus == Status.MAP_NOT_FOUND)
				drawMessageAtCenter(g, Text.MODE_MAKE_MAP_NO_MAP, Color.RED);
			else
				drawMessageAtCenter(g, Text.MODE_MAKE_MAP, Color.GREEN);
			return;
		}

		switch (gameStatus) {
			case WIN:
				drawMessageAtCenter(g, Text.GAME_WIN, Color.BLUE);
				return;
			case GAME_OVER:
				drawMessageAtCenter(g, Text.GAME_OVER, Color.RED);
				return;
			case MAP_NOT_FOUND:
				drawMessageAtCenter(g, Text.MAP_NOT_FOUND, Color.RED);
				return;
			default:
				return;
		}
	}
}
