package graphic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;

import javax.swing.JPanel;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MyButton level1, level2, level3, arena;

	Image sfondo, liv1, liv1o, liv2, liv2o, liv3, liv3o, livArena, livArenao,titolo;

	public final static int BUTTON_WIDTH = 204, BUTTON_HEIGHT = 74, ARENA_WIDTH = 172;

	public MainMenu() {
		initGui();
		eventHandler();
	}

	private void initGui() {
		this.setFocusable(true);
		setSize(Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);
		
		level1 = new MyButton(Window.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2,
				Window.SCREEN_HEIGHT / 2 - 100, BUTTON_WIDTH, BUTTON_HEIGHT);
		arena = new MyButton(Window.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2,
				Window.SCREEN_HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		level3 = new MyButton(Window.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2,
				Window.SCREEN_HEIGHT / 2 + 100, BUTTON_WIDTH, BUTTON_HEIGHT);
		level2 = new MyButton(Window.SCREEN_WIDTH / 2 - BUTTON_WIDTH / 2,
				Window.SCREEN_HEIGHT / 2 + 200, ARENA_WIDTH, BUTTON_HEIGHT);

		sfondo = loadAssets("backGroundMainMenu.jpg");
		liv1 = loadAssets("level1.png");
		liv1o = loadAssets("level1Over.png");
		liv2 = loadAssets("level2.png");
		liv2o = loadAssets("level2Over.png");
		liv3 = loadAssets("level3.png");
		liv3o = loadAssets("level3Over.png");
		livArena = loadAssets("Arena.png");
		livArenao = loadAssets("ArenaOver.png");
		titolo = loadAssets("mainTitle.png");

	}

	public Image loadAssets(String path) {
		URL url = this.getClass().getResource(path);
		Image img = Window.TK.getImage(url);

		return img;
	}

	private void eventHandler() {
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				if (level1.isOver(mouseX, mouseY)) {
					LivelloMarco livMarco = new LivelloMarco();
					Window.SCREEN_MANAGER = new Window();
					Window.SCREEN_MANAGER.setContentPane(livMarco);
					Window.SCREEN_MANAGER.setVisible(true);
				}/*
				if (level2.isOver(mouseX, mouseY)) {
					JFrame f = new MyFrame();
					f.setVisible(true);
				}
				if(level3.isOver(mouseX,mouseY)) {
					ArenaClient livArena = new ArenaClient();
					Window.SCREEN_MANAGER = new Window();
					Window.SCREEN_MANAGER.setContentPane(livArena);
					Window.SCREEN_MANAGER.setVisible(true);
				}*/
				if (arena.isOver(mouseX, mouseY)) {
					Arena livArena = new ArenaClient();   //new ArenaClient();
					Window.SCREEN_MANAGER = new Window();
					Window.SCREEN_MANAGER.setContentPane(livArena);
					Window.SCREEN_MANAGER.setVisible(true);
				}
			}

		});
		this.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();
				if (level1.isOver(mouseX, mouseY)) {
					level1.id=1;
					level2.id=0;
					level3.id=0;
					arena.id=0;
				} /*else if(level2.isOver(mouseX, mouseY)){
					level2.id=1;
					level1.id=0;
					level3.id=0;
					arena.id=0;
				}
				else if(level3.isOver(mouseX, mouseY))
				{
					level3.id=1;
					level2.id=0;
					level1.id=0;
					arena.id=0;
				} */
				else if(arena.isOver(mouseX, mouseY)){
					arena.id=1;
					level1.id=0;
					level2.id=0;
					level3.id=0;
				}
				else{
					level1.id=0;
					level2.id=0;
					level3.id=0;
					arena.id=0;
				}
				repaint();
			}

		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(sfondo, 0, 0, Window.SCREEN_WIDTH,      //Image sfondo, liv1, liv1o, liv2, liv2o, liv3, liv3o, livArena, livArenao;
				Window.SCREEN_HEIGHT, this);
		if (level1.id == 0)
			g.drawImage(liv1, level1.getX(), level1.getY(),
					level1.getWidth(), level1.getHeight(), this);
		else
			g.drawImage(liv1o, level1.getX(), level1.getY(),
					level1.getWidth(), level1.getHeight(), this);
		/*if (level2.id == 0)
			g.drawImage(liv2, level2.getX(), level2.getY(), this);
		else
			g.drawImage(liv2o, level2.getX(), level2.getY(), this);
		if (level3.id == 0)
			g.drawImage(liv3, level3.getX(), level3.getY(), this);
		else
			g.drawImage(liv3o, level3.getX(), level3.getY(), this);*/
		if (arena.id == 0)
			g.drawImage(livArena, arena.getX(), arena.getY(), this);
		else
			g.drawImage(livArenao, arena.getX(), arena.getY(), this);

		g.drawImage(titolo, Window.SCREEN_WIDTH / 2
				- titolo.getWidth(this) / 2,
				Window.SCREEN_HEIGHT / 2 - 300, this);
	}

}
