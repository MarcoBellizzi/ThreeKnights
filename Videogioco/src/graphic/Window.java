package graphic;

import java.awt.*;
import javax.swing.*;

public class Window extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	
	public static final Toolkit TK = Toolkit.getDefaultToolkit();
	public static final int SCREEN_WIDTH = TK.getScreenSize().width;
	public static final int SCREEN_HEIGHT = TK.getScreenSize().height;
	
	static Window SCREEN_MANAGER;
	static MainMenu menu;
	
	public static void main(String[] args) {
		SCREEN_MANAGER = new Window();
		menu = new MainMenu();
		SCREEN_MANAGER.setContentPane(menu);
		SCREEN_MANAGER.setVisible(true);
		SCREEN_MANAGER.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Window() {	
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setLocation(0,0);
	}
	
}
