package graphic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import connection.MyServer;
import logic.Angel;
import logic.Character.azione;
import logic.Drago;
import logic.DragoVerde;
import logic.Enemy;
import logic.Entity.direzione;
import logic.Gun;
import logic.MainCharacter;
import logic.StaticThing;
import logic.World;
import threads.TimerArena;
import threads.TimerPannello;



public class Arena extends JPanel {

	private static final long serialVersionUID = 1L;

	public MyServer server;
	public ServerSocket serverSocket;
	public Socket incoming;

	public BufferedReader in;
	public PrintWriter out;

	public World w;
	public int delta;
	
	public boolean complete;

	private int yFloor;
	
	public MainCharacter Marco;
	public MainCharacter Fabio;

	public List<MainCharacter> listaMainCharacter;
	public List<Enemy> listaNemici;
	public List<Drago> listaDraghi;
	public List<Angel> listaAngeli;
	public List<Gun> listaSfere;
	public List<StaticThing>listaStaticThing;

	public Drago drago1;

	public Drago drago2;
	public Drago drago3;
	public DragoVerde dragoVerde1;

	public Drago drago4;
	public DragoVerde dragoVerde2;
	public DragoVerde dragoVerde3;
	public Angel angel1;
	public Angel angel2;


	Image arena;

	Image r_stai_d;
	Image r_stai_s;
	Image r_stai_spada_d;
	Image r_stai_spada_s;
	Image r_corri_d;
	Image r_corri_s;
	Image r_attacca_d;
	Image r_attacca_s;

	Image death;
	
	Image d_stai_d;
	Image d_stai_s;
	Image d_cammina_d;
	Image d_cammina_s;
	Image d_attacca_d;
	Image d_attacca_s;
	Image d_salta_d;
	Image d_salta_s;

	Image dv_stai_d;
	Image dv_stai_s;
	Image dv_cammina_d;
	Image dv_cammina_s;
	Image dv_attacca_d;
	Image dv_attacca_s;
	Image dv_salta_d;
	Image dv_salta_s;

	Image a_stai;
	Image a_vola;
	Image a_attacca_d;
	Image a_attacca_s;

	Image meteora0;
	Image meteora1;
	Image meteora2;

	Image ball;
	Image fuocoD;
	Image fuocoS;

	Image green;
	Image red;
	
	Image levelComplete;
	
	Image iboss;

	public StaticThing floor;
	public StaticThing wall1;
	public StaticThing wall2;

	public TimerPannello timerPannello;

	public TimerArena timerArena;

	public Arena() {
		super();
		initGUI();
		if(!(this instanceof ArenaClient)) {
			initEH();
			connettiServer();
		}
	}

	public void initGUI() {
		this.setFocusable(true);

		arena = loadAssets("arenaSfondo.png");

		r_stai_d = loadAssets("r_stai_d.gif");
		r_stai_s = loadAssets("r_stai_s.gif");
		r_stai_spada_d = loadAssets("r_stai_spada_d.gif");
		r_stai_spada_s = loadAssets("r_stai_spada_s.gif");
		r_corri_d = loadAssets("r_corri_d.gif");
		r_corri_s = loadAssets("r_corri_s.gif");
		r_attacca_d = loadAssets("r_attacca_d.gif");
		r_attacca_s = loadAssets("r_attacca_s.gif");
		
		death = loadAssets("dead.png");

		d_stai_d = loadAssets("d_stai_d.gif");
		d_stai_s = loadAssets("d_stai_s.gif");
		d_cammina_d = loadAssets("d_cammina_d.gif");
		d_cammina_s = loadAssets("d_cammina_s.gif");
		d_attacca_d = loadAssets("d_attacca_d.gif");
		d_attacca_s = loadAssets("d_attacca_s.gif");
		d_salta_d = loadAssets("d_salta_d.gif");
		d_salta_s = loadAssets("d_salta_s.gif");

		dv_stai_d = loadAssets("dv_stai_d.gif");
		dv_stai_s = loadAssets("dv_stai_s.gif");
		dv_cammina_d = loadAssets("dv_cammina_d.gif");
		dv_cammina_s = loadAssets("dv_cammina_s.gif");
		dv_attacca_d = loadAssets("dv_attacca_d.gif");
		dv_attacca_s = loadAssets("dv_attacca_s.gif");
		dv_salta_d = loadAssets("dv_salta_d.gif");
		dv_salta_s = loadAssets("dv_salta_s.gif");

		a_stai = loadAssets("a_stai.gif");
		a_vola = loadAssets("a_vola.gif");
		a_attacca_d = loadAssets("a_attacca_d.gif");
		a_attacca_s = loadAssets("a_attacca_s.gif");

		fuocoS = loadAssets("fire_ball.gif");
		fuocoD = loadAssets("fire_ball_d.gif");
		ball = loadAssets("ball.gif");

		meteora0 = loadAssets("meteora0.png");
		meteora1 = loadAssets("meteora1.png");
		meteora2 = loadAssets("meteora2.png");

		green = loadAssets("green.png");
		red = loadAssets("red.png");
		
		iboss = loadAssets("boss.gif");
		
		levelComplete = loadAssets("levelComplete.png");

		w = new World(3000,Window.SCREEN_HEIGHT); 
		delta = 0;

		complete = false;
		
		yFloor =(int) (Window.SCREEN_HEIGHT/1.28);
		
		Marco = new MainCharacter(400,yFloor-200,10000,100,140);
		Marco.getSfera().setVelocita(7);
		Marco.setDirezione(direzione.GIU);   //non so perchè non se lo prende nel costruttore

		Fabio = new MainCharacter(700,yFloor-200,10000,100,140);
		Fabio.getSfera().setVelocita(7);
		Fabio.setDirezione(direzione.GIU);

		listaMainCharacter = new ArrayList<MainCharacter>();
		listaMainCharacter.add(Marco);
		listaMainCharacter.add(Fabio);

		drago1 = new Drago(1500,yFloor-370,5000,360,400,8,6);
		drago1.setAttivo(true);

		drago2 = new Drago(700,yFloor-370,5000,360,400,7,6);
		dragoVerde1 = new DragoVerde(1500,yFloor-370,2000,360,400,5,6);
		drago3 = new Drago(2200,yFloor-370,5000,360,400,7,6);

		dragoVerde2 = new DragoVerde(500,yFloor-370,2000,360,400,4,6);
		angel1 = new Angel(1000,yFloor-200,3000,140,200,5);      // 5 -> modificare l' if nel timer arena
		angel1.getSfera().setVelocita(7);
		angel1.getMeteora1().setVelocita(6);
		angel1.getMeteora2().setVelocita(7);
		drago4 = new Drago(1500,yFloor-370,5000,360,400,6,6);
		angel2 = new Angel(2000,yFloor-200,3000,140,200,4);
		angel2.getSfera().setVelocita(7);
		angel2.getMeteora1().setVelocita(6);
		angel2.getMeteora2().setVelocita(7);
		dragoVerde3 = new DragoVerde(2500,yFloor-370,2000,360,400,4,6);

		listaNemici = new ArrayList<Enemy>();
		listaNemici.add(drago1);
		listaNemici.add(drago2);
		listaNemici.add(drago3);
		listaNemici.add(drago4);
		listaNemici.add(dragoVerde1);
		listaNemici.add(dragoVerde2);
		listaNemici.add(dragoVerde3);
		listaNemici.add(angel1);
		listaNemici.add(angel2);

		listaDraghi = new ArrayList<Drago>();
		listaDraghi.add(drago1);
		listaDraghi.add(drago2);
		listaDraghi.add(drago3);
		listaDraghi.add(drago4);
		listaDraghi.add(dragoVerde1);
		listaDraghi.add(dragoVerde2);
		listaDraghi.add(dragoVerde3);

		listaAngeli = new ArrayList<Angel>();
		listaAngeli.add(angel1);
		listaAngeli.add(angel2);

		floor = new StaticThing(0,yFloor,3200,100);
		wall1 = new StaticThing(0,0,200,Window.SCREEN_HEIGHT);
		wall2 = new StaticThing(3000,0,200,Window.SCREEN_HEIGHT);

		listaSfere = new ArrayList<Gun>();
		listaSfere.add(Marco.getSfera());
		listaSfere.add(Fabio.getSfera());
		listaSfere.add(drago1.getSfera());
		listaSfere.add(drago2.getSfera());
		listaSfere.add(drago3.getSfera());
		listaSfere.add(drago4.getSfera());
		listaSfere.add(dragoVerde1.getSfera());
		listaSfere.add(dragoVerde2.getSfera());
		listaSfere.add(dragoVerde3.getSfera());
		listaSfere.add(angel1.getSfera());
		listaSfere.add(angel1.getMeteora1());
		listaSfere.add(angel1.getMeteora2());
		listaSfere.add(angel2.getSfera());
		listaSfere.add(angel2.getMeteora1());
		listaSfere.add(angel2.getMeteora2());
		
		listaStaticThing = new ArrayList<StaticThing>();
		listaStaticThing.add(floor);
		listaStaticThing.add(wall1);
		listaStaticThing.add(wall2);

		timerPannello = new TimerPannello(this);
		timerPannello.start();

		timerArena = new TimerArena(this);
	}

	public void initEH() {
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				switch(e.getKeyCode()) {
				case(KeyEvent.VK_RIGHT) : {
					Marco.setStato(azione.CAMMINADX);
					Marco.setDirezione(direzione.DESTRA);
					out.println("g");
					out.flush();
					break;
				}
				case(KeyEvent.VK_LEFT) : {
					Marco.setStato(azione.CAMMINASX);
					Marco.setDirezione(direzione.SINISTRA);
					out.println("d");
					out.flush();
					break;
				}
				case(KeyEvent.VK_UP) : {
					if(Marco.collision(floor)) { 
						if(Marco.getDirezione()==direzione.GIU) {
							Marco.setDirezione(direzione.SU);
						}
						Marco.resetCont();
					}
					out.println("r");
					out.flush();
					break;
				}
				case(KeyEvent.VK_A) : {
					Marco.setStato(azione.ATTACCASX);
					for(Enemy enemy : listaNemici) {
						if(enemy.collision(Marco) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					out.println("q");
					out.flush();
					break;
				}
				case(KeyEvent.VK_S) : {
					Marco.setStato(azione.ATTACCADX);
					for(Enemy enemy : listaNemici) {
						if(enemy.collision(Marco) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					out.println("w");
					out.flush();
					break;
				} 
				case(KeyEvent.VK_X) : {
					if(Marco.getPower()>300) {
						Marco.getSfera().setVisible(false);
						if(!Marco.getCarica())
							Marco.getSfera().ripristinaBonus();
						Marco.setCarica(true);
					}
					out.println("c");
					out.flush();
					break;
				} 
				case(KeyEvent.VK_Z) : {
					if(Marco.getPower()>300) {
						Marco.getSfera().setVisible(false);
						if(!Marco.getCarica())
							Marco.getSfera().ripristinaBonus();
						Marco.setCarica(true);
					}
					out.println("v");
					out.flush();
					break;
				}
				case(KeyEvent.VK_P) : {     //???
					Window f = new Window();
					f.setVisible(true);
				}

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);

				switch(e.getKeyCode()) {
				case(KeyEvent.VK_RIGHT) :{
					Marco.setStato(azione.FERMOD);
					Marco.setDirezione(direzione.GIU);
					out.println("G");
					out.flush();
					break;
				}
				case(KeyEvent.VK_LEFT) : {
					Marco.setStato(azione.FERMOS); 
					Marco.setDirezione(direzione.GIU);
					out.println("D");
					out.flush();
					break;
				}
				case(KeyEvent.VK_A) : {
					Marco.setStato(azione.FERMOS); 
					out.println("Q");
					out.flush();
					break;
				}
				case(KeyEvent.VK_S) : {
					Marco.setStato(azione.FERMOD);
					out.println("W");
					out.flush();
					break;
				}

				case(KeyEvent.VK_X) : { 
					if(Marco.getCarica()) {
						Marco.sfera.setX(Marco.getX()+101);
						Marco.sfera.setY(Marco.getY()+10);
						Marco.sfera.setDirezione(direzione.DESTRA);
						Marco.sfera.setVisible(true);
						Marco.setCarica(false);
						Marco.resetPower();
					}
					out.println("C");
					out.flush();
					break;
				}
				case(KeyEvent.VK_Z) : {
					if(Marco.getCarica()) {
						Marco.sfera.setX(Marco.getX()-1);
						Marco.sfera.setY(Marco.getY()+10);
						Marco.sfera.setDirezione(direzione.SINISTRA);
						Marco.sfera.setVisible(true);
						Marco.setCarica(false);
						Marco.resetPower();
					}
					out.println("V");
					out.flush();
					break;
				} 
				}
			}

		});
	}


	public void connettiServer() {
		try {
			serverSocket = new ServerSocket(4444);
			incoming = serverSocket.accept();

			System.out.println("client connesso");

			in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			out = new PrintWriter(incoming.getOutputStream());

			server = new MyServer(this,incoming,in); 
			server.start();
			timerArena.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Image loadAssets(String path) {
		URL url = this.getClass().getResource(path);
		Image img = Window.TK.getImage(url);

		return img;
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.drawImage(arena, delta-200, 0, 3200, Window.SCREEN_HEIGHT, this);

		if(!(this instanceof ArenaClient))
			g.drawString("Arena",100,100);
		else
			g.drawString("ArenaClient", 100, 100);

		for(Enemy e : listaNemici) {

			if(e.getAttivo()) {

				if(e instanceof Angel) {
					switch(e.getStato()) {
					case FERMOD : g.drawImage(a_stai, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
					case FERMOS : break;
					case ATTACCADX : g.drawImage(a_attacca_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
					case ATTACCASX : g.drawImage(a_attacca_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
					case CAMMINADX : break;
					case CAMMINASX : break;
					case SALTAD : g.drawImage(a_vola, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
					case SALTAS : break;
					}
					
					g.drawImage(green, e.getX()+delta, e.getY()-60, (int)(e.getLife()*100/e.getLifeMax()+2), 15, this);
					g.drawImage(red, e.getX()+delta+100, e.getY()-60, (int) (-100+e.getLife()*100/e.getLifeMax()), 15, this);

					
					if(e.getSfera().getVisible()) {
						g.drawImage(meteora0, e.getSfera().getX()+delta, e.getSfera().getY(), 
								e.getSfera().getWidth(), e.getSfera().getHeight(), this);
					}
					if(((Angel) e).getMeteora1().getVisible()) {
						g.drawImage(meteora1, ((Angel) e).getMeteora1().getX()+delta, ((Angel) e).getMeteora1().getY(), 
								((Angel) e).getMeteora1().getWidth(), ((Angel) e).getMeteora1().getHeight(), this);
					}
					if(((Angel) e).getMeteora2().getVisible()) {
						g.drawImage(meteora2, ((Angel) e).getMeteora2().getX()+delta, ((Angel) e).getMeteora2().getY(), 
								((Angel) e).getMeteora2().getWidth(), ((Angel) e).getMeteora2().getHeight(), this);
					}
				}
				if(e instanceof Drago ) {
					if(e instanceof DragoVerde) {
						switch(e.getStato()) {
						case FERMOD : g.drawImage(dv_stai_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case FERMOS : g.drawImage(dv_stai_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case ATTACCADX : g.drawImage(dv_attacca_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case ATTACCASX : g.drawImage(dv_attacca_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case CAMMINADX : g.drawImage(dv_cammina_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case CAMMINASX : g.drawImage(dv_cammina_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case SALTAD : g.drawImage(dv_salta_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case SALTAS : g.drawImage(dv_salta_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						}

						g.drawImage(green, e.getX()+delta, e.getY()-60, (int)(e.getLife()*100/e.getLifeMax()+2), 15, this);
						g.drawImage(red, e.getX()+delta+100, e.getY()-60, (int) (-100+e.getLife()*100/e.getLifeMax()), 15, this);

						
						if(e.sfera.getVisible()) {
							if(e.sfera.getDirezione()==direzione.SINISTRA)
								g.drawImage(fuocoS, e.sfera.getX()+delta, e.sfera.getY(), 100, 70, this);
							else 
								g.drawImage(fuocoD, e.sfera.getX()+delta, e.sfera.getY(), 100, 70, this);
						}
					}
					else {
						switch(e.getStato()) {
						case FERMOD : g.drawImage(d_stai_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case FERMOS : g.drawImage(d_stai_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case ATTACCADX : g.drawImage(d_attacca_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case ATTACCASX : g.drawImage(d_attacca_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case CAMMINADX : g.drawImage(d_cammina_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case CAMMINASX : g.drawImage(d_cammina_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case SALTAD : g.drawImage(d_salta_d, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						case SALTAS : g.drawImage(d_salta_s, e.getX()+delta, e.getY(), e.getWidth(), e.getHeight(), this); break;
						}
						g.drawImage(green, e.getX()+delta, e.getY()-60, (int)(e.getLife()*100/e.getLifeMax()+2), 15, this);
						g.drawImage(red, e.getX()+delta+100, e.getY()-60, (int) (-100+e.getLife()*100/e.getLifeMax()), 15, this);


						if(e.sfera.getVisible()) {
							if(e.sfera.getDirezione()==direzione.SINISTRA)
								g.drawImage(fuocoS, e.sfera.getX()+delta, e.sfera.getY(), 100, 70, this);
							else 
								g.drawImage(fuocoD, e.sfera.getX()+delta, e.sfera.getY(), 100, 70, this);
						}
					}
				}
			}
		}



		for(MainCharacter character : listaMainCharacter) {

			if(character.getLife()>0) { 

				switch(character.getStato()) {
				case FERMOD : g.drawImage(r_stai_d, character.getX()+delta-65, character.getY()-40, 260, 200, this); break;
				case FERMOS : g.drawImage(r_stai_s, character.getX()+delta-65, character.getY()-40, 260, 200, this); break;
				case ATTACCADX : g.drawImage(r_attacca_d, character.getX()+delta-65, character.getY()-40, 260, 200, this); break;
				case ATTACCASX : g.drawImage(r_attacca_s, character.getX()+delta-65, character.getY()-40, 260, 200, this); break;
				case CAMMINADX : g.drawImage(r_corri_d, character.getX()+delta-65, character.getY()-40, 260, 200, this); break;
				case CAMMINASX : g.drawImage(r_corri_s, character.getX()+delta-65, character.getY()-40, 260, 200, this); break;
				case SALTAD : break;
				case SALTAS : break;
				}

				g.drawImage(green, character.getX()+delta, character.getY()-60, (int)(character.getLife()*100/character.getLifeMax()+2), 15, this);
				g.drawImage(red, character.getX()+delta+100, character.getY()-60, (int) (character.getLife()*100/character.getLifeMax()-100), 15, this);

				if(character.getSfera().getVisible()) {
					g.drawImage(ball,character.getSfera().getX()+delta,character.sfera.getY(),70,70,this);
				}
				if(character.getCarica()) {
					g.drawImage(ball,character.getX()+delta+15,character.getY()+10,70,70,this);
					g.drawString(""+(character.getSfera().getPotenza()+character.getSfera().getBonus()), character.getX()+delta, character.getY()-50);
				}

			}
		}

		if(complete)
			g.drawImage(levelComplete, 0, 100, Window.SCREEN_WIDTH, 400, this);
		
		//g.drawImage(iboss, 500+delta, yFloor-300, 500, 500, this);

	}

}
