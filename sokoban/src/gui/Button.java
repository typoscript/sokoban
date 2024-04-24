package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton {
	public Button(String text) {
		super(text);
		setFocusPainted(false);
	    setBorder(BorderFactory.createLineBorder(Color.BROWN));

		setBackground(Color.LIGHT_BROWN);
		setForeground(Color.DARK_BROWN);

		setFont(new java.awt.Font(Font.FAMILY, Font.STYLE, 13));

		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.BROWN);
			}
			
			public void mouseExited(MouseEvent e) {
				setBackground(Color.LIGHT_BROWN);
			}
		});
	}
}
