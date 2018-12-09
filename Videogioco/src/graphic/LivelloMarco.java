package graphic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import logic.Drago;
import logic.DragoVerde;
import logic.Enemy;
import logic.Entity.direzione;
import logic.Gun;
import threads.TimerAngelo;
import threads.TimerCatena;
import threads.TimerDrago;
import threads.TimerPannello;
import threads.TimerSpostamento;
import threads.TimerSfere;
import logic.MainCharacter;
import logic.StaticThing;
import logic.Thing;
import logic.Angel;
import logic.Breakable;
import logic.Character.azione;
import logic.World;

public class LivelloMarco extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public World w;
	public int delta;
	
	public boolean completeDrago;
	public boolean completeAngel;

	public List<Gun> listaSfere;
	public List<Enemy> listaNemici;
	public List<Drago> listaDraghi;
	public List<Angel> listaAngeli;
	public List<Breakable> listaOggettiBreakable;
	public List<StaticThing> listaOggettiStatici;

	public MainCharacter Rhode;
	
	public Drago drago1;
	public Drago drago2;
	public Drago drago3;
	public Drago drago4;
	public Drago drago5;
	public DragoVerde dragoVerde1;
	public DragoVerde dragoVerde2;
	public DragoVerde dragoVerde3;
	public Angel angelo1;
	public Angel angelo2;
	public Angel angelo3;
	
	private int yBeach;
	
	public StaticThing beach;
	public StaticThing brik;
	public StaticThing piattaforma;
	public StaticThing chain;
	public StaticThing wall1;
	public StaticThing wall2;
	
	public Breakable cassa1;
	public Breakable cassa2;
	
	public Thing fireArea;

	Image mare;
	Image tramonto;
	Image notte;
	
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
	
	Image fuocoD;
	Image fuocoS;
	Image ball;
	
	Image a_stai;
	Image a_vola;
	Image a_attacca_d;
	Image a_attacca_s;
	
	Image meteora0;
	Image meteora1;
	Image meteora2;
	
	Image i_mattone;
	Image i_cassa;
	Image i_piattaforma;
	Image i_catena;
	
	Image falo;
	
	Image green;
	Image red;
	
	Image levelComplete;
	
	public TimerPannello timerPannello;
	
	public TimerSpostamento spostamento;
	
	public TimerSfere timerSfere;
	
	public TimerDrago timerDrago;
	
	public TimerAngelo timerAngelo;
	
	public TimerCatena timerCatena;
	
	
	public LivelloMarco() {
		super();
		initGUI();
		initEH();
	}
	
	public void initGUI() {
		this.setFocusable(true);
		
		mare = loadAssets("mare.jpg");
		tramonto = loadAssets("tramonto.jpg");
		notte = loadAssets("notte.jpg");
		
	//	arena = loadAssets("arena.png");
		
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
		
		fuocoS = loadAssets("fire_ball.gif");
		fuocoD = loadAssets("fire_ball_d.gif");
		ball = loadAssets("ball.gif");
		
		a_stai = loadAssets("a_stai.gif");
		a_vola = loadAssets("a_vola.gif");
		a_attacca_d = loadAssets("a_attacca_d.gif");
		a_attacca_s = loadAssets("a_attacca_s.gif");
		
		meteora0 = loadAssets("meteora0.png");
		meteora1 = loadAssets("meteora1.png");
		meteora2 = loadAssets("meteora2.png");
		
		i_mattone = loadAssets("mattone.png");
		i_cassa = loadAssets("breakable.png");
		i_piattaforma = loadAssets("piattaforma.png");
		i_catena = loadAssets("catena.png");
		
		falo = loadAssets("fuoco.gif");
		
		green = loadAssets("green.png");
		red = loadAssets("red.png");
		
		levelComplete = loadAssets("levelComplete.png");
		
		w = new World(15000,Window.SCREEN_HEIGHT);  
		delta = 0;
		
		completeDrago = false;
		completeAngel = false;
		
		yBeach = (int) (Window.SCREEN_HEIGHT/1.28);
		
		Rhode = new MainCharacter(Window.SCREEN_WIDTH/2,yBeach-150,10000,100,140);
		Rhode.setDirezione(direzione.GIU);   // non so perchè non se lo prende nel costruttore
		
		listaNemici = new ArrayList<Enemy>();  
		listaDraghi = new ArrayList<Drago>();
		listaAngeli = new ArrayList<Angel>();
		
		drago1 = new Drago(6700,yBeach-370,5000,360,400,4);
		drago2 = new Drago(8000,yBeach-370,5000,360,400,4);
		drago3 = new Drago(8200,yBeach-370,5000,360,400,3);
		drago4 = new Drago(9500,yBeach-370,5000,360,400,3);
		drago5 = new Drago(14500,yBeach-370,5000,360,400,2);
		dragoVerde1 = new DragoVerde(9700,yBeach-370,1000,360,400,2);
		dragoVerde2 = new DragoVerde(12500,yBeach-370,1000,360,400,2);
		dragoVerde3 = new DragoVerde(14000,yBeach-370,1000,360,400,1);
		angelo1 = new Angel(11500,yBeach-200,2000,140,200);
		angelo2 = new Angel(12500,yBeach-200,2000,140,200);
		angelo3 = new Angel(14000,yBeach-200,2000,140,200);
		
		listaNemici.add(drago1);
		listaNemici.add(drago2);
		listaNemici.add(drago3);
		listaNemici.add(drago4);
		listaNemici.add(drago5);
		listaNemici.add(dragoVerde1);
		listaNemici.add(dragoVerde2);
		listaNemici.add(dragoVerde3);
		listaNemici.add(angelo1);
		listaNemici.add(angelo2);
		listaNemici.add(angelo3);
		
		listaDraghi.add(drago1);
		listaDraghi.add(drago2);
		listaDraghi.add(drago3);
		listaDraghi.add(drago4);
		listaDraghi.add(drago5);
		listaDraghi.add(dragoVerde1);
		listaDraghi.add(dragoVerde2);
		listaDraghi.add(dragoVerde3);
		
		listaAngeli.add(angelo1);
		listaAngeli.add(angelo2);
		listaAngeli.add(angelo3);
		
		beach = new StaticThing(0,yBeach,w.getdimX(),100);
		wall1 = new StaticThing(0,0,200,Window.SCREEN_HEIGHT);
		wall2 = new StaticThing(14800,0,200,Window.SCREEN_HEIGHT);
		brik = new StaticThing(1000,yBeach-200,200,200);
		piattaforma = new StaticThing(2000,yBeach-300,1000,50);
		fireArea = new Thing(2000,yBeach-200,1000,200);
		chain = new StaticThing(4700,0,100,Window.SCREEN_HEIGHT);

		listaOggettiStatici = new ArrayList<StaticThing>();
		
		listaOggettiStatici.add(beach);
		listaOggettiStatici.add(wall1);
		listaOggettiStatici.add(wall2);
		listaOggettiStatici.add(brik);
		listaOggettiStatici.add(piattaforma);  
		listaOggettiStatici.add(chain);
		
		listaOggettiBreakable = new ArrayList<Breakable>();
		
		cassa1 = new Breakable(3800,yBeach-500,500,500,1000);
		cassa2 = new Breakable(5000,yBeach-300,200,200,500);
		
		listaOggettiBreakable.add(cassa1);
		listaOggettiBreakable.add(cassa2);
		
		listaSfere = new ArrayList<Gun>();
		
		listaSfere.add(Rhode.getSfera());
		listaSfere.add(drago1.getSfera());
		listaSfere.add(drago2.getSfera());
		listaSfere.add(drago3.getSfera());
		listaSfere.add(drago4.getSfera());
		listaSfere.add(drago5.getSfera());
		listaSfere.add(dragoVerde1.getSfera());
		listaSfere.add(dragoVerde2.getSfera());
		listaSfere.add(dragoVerde3.getSfera());
		listaSfere.add(angelo1.getSfera());
		listaSfere.add(angelo1.getMeteora1());
		listaSfere.add(angelo1.getMeteora2());
		listaSfere.add(angelo2.getSfera());
		listaSfere.add(angelo2.getMeteora1());
		listaSfere.add(angelo2.getMeteora2());
		listaSfere.add(angelo3.getSfera());
		listaSfere.add(angelo3.getMeteora1());
		listaSfere.add(angelo3.getMeteora2());
		
		spostamento = new TimerSpostamento(this);
		timerDrago = new TimerDrago(this);
		timerAngelo = new TimerAngelo(this);
		timerSfere = new TimerSfere(this);
		timerCatena = new TimerCatena(chain,this);
		timerPannello = new TimerPannello(this);
		
		spostamento.start();
		timerDrago.start();
		timerSfere.start();
		timerAngelo.start();
		timerPannello.start();
		
		iniziaMusica();

		
	}
	
	public void iniziaMusica() {
		try {
			URL url = this.getClass().getResource("Videogame2.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.loop(1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initEH() {        
		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				switch(e.getKeyCode()) {
				case(KeyEvent.VK_RIGHT) : {
					Rhode.setStato(azione.CAMMINADX);
					Rhode.setDirezione(direzione.DESTRA); 
					break;
				}
				case(KeyEvent.VK_LEFT) : {
					Rhode.setStato(azione.CAMMINASX);
					Rhode.setDirezione(direzione.SINISTRA);
					break;
				}
				case(KeyEvent.VK_UP) : {
					if(Rhode.collision(beach) || Rhode.collision(brik) || Rhode.collision(piattaforma)) {   
						if(Rhode.getDirezione()==direzione.GIU) {
							Rhode.setDirezione(direzione.SU);
						}
						Rhode.resetCont();
						break;
					}	
					break;
				}
				case(KeyEvent.VK_A) : {
					Rhode.setStato(azione.ATTACCASX);
					for(Enemy enemy : listaNemici) {
						if(enemy.collision(Rhode))
							enemy.reduceLife(30);  //30
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					for(Breakable cassa : listaOggettiBreakable) {
						if(cassa.collision(Rhode))
							cassa.reduceLife(30);  //30
						if(cassa.getLife()<=0)
							listaOggettiBreakable.remove(cassa); 
					}
					break;
				}
				case(KeyEvent.VK_S) : {
					Rhode.setStato(azione.ATTACCADX);
					for(Enemy enemy : listaNemici) {
						if(enemy.collision(Rhode))
							enemy.reduceLife(30);  //30
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					for(Breakable cassa : listaOggettiBreakable) {
						if(cassa.collision(Rhode))
							cassa.reduceLife(30);   //30
						if(cassa.getLife()<=0)
							listaOggettiBreakable.remove(cassa);
					}
					break;
				} 
				case(KeyEvent.VK_X) : {
					if(Rhode.getPower()>300) {
						Rhode.getSfera().setVisible(false);
						if(!Rhode.getCarica())
							Rhode.getSfera().ripristinaBonus();
						Rhode.setCarica(true);
					}
					break;
				} 
				case(KeyEvent.VK_Z) : {
					if(Rhode.getPower()>300) {
						Rhode.getSfera().setVisible(false);
						if(!Rhode.getCarica())
							Rhode.getSfera().ripristinaBonus();
						Rhode.setCarica(true);
					}
					break;
				}
				case(KeyEvent.VK_P) : {
					Window.SCREEN_MANAGER = new Window();
					LivelloMarco l = new LivelloMarco();
					Window.SCREEN_MANAGER.setContentPane(l);
					Window.SCREEN_MANAGER.setVisible(true);
					
				}
				
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				
				switch(e.getKeyCode()) {
				case(KeyEvent.VK_RIGHT) :{
					Rhode.setStato(azione.FERMOD);
					Rhode.setDirezione(direzione.GIU);
					break;
				}
				case(KeyEvent.VK_LEFT) : {
					Rhode.setStato(azione.FERMOS); 
					Rhode.setDirezione(direzione.GIU);
					break;
				}
				case(KeyEvent.VK_A) : {
					Rhode.setStato(azione.FERMOS); 
					break;
				}
				case(KeyEvent.VK_S) : {
					Rhode.setStato(azione.FERMOD); 
					break;
				}

				case(KeyEvent.VK_X) : { 
					if(Rhode.getCarica()) {
						Rhode.sfera.setX(Rhode.getX()+101);
						Rhode.sfera.setY(Rhode.getY()+10);
						Rhode.sfera.setDirezione(direzione.DESTRA);
						Rhode.sfera.setVisible(true);
						Rhode.setCarica(false);
						Rhode.resetPower();
					}
					break;
				}
				case(KeyEvent.VK_Z) : {
					if(Rhode.getCarica()) {
						Rhode.sfera.setX(Rhode.getX()-1);
						Rhode.sfera.setY(Rhode.getY()+10);
						Rhode.sfera.setDirezione(direzione.SINISTRA);
						Rhode.sfera.setVisible(true);
						Rhode.setCarica(false);
						Rhode.resetPower();
					}
					break;
				} 
				}
			}

		});
	}
	
	public Image loadAssets(String path) {
		URL url = this.getClass().getResource(path);
		Image img = Toolkit.getDefaultToolkit().getImage(url);
		return img;
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(delta >= -5000) {
			g.drawImage(mare, delta-200, 0, 6700, Window.SCREEN_HEIGHT, this);
		}
		else {
			if(delta >= -10000) {
				g.drawImage(tramonto, delta+4800, 0, 6700, Window.SCREEN_HEIGHT, this);
			}
			else {
				g.drawImage(notte, delta+9800, 0, 6000, Window.SCREEN_HEIGHT, this);
			}
		}
		
		g.drawString("tenere premuto a/s per attaccare con la spada",  delta+3200, 200);
		g.drawString("tenere premuto z/x per caricare l'energia sferica", 4300+delta, 200);
		
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

		g.drawImage(i_mattone, brik.getX()+delta, brik.getY(), brik.getWidth(), brik.getHeight(), this);
		g.drawImage(i_piattaforma, piattaforma.getX()+delta, piattaforma.getY(), piattaforma.getWidth(), piattaforma.getHeight(), this);
		g.drawImage(i_catena, chain.getX()+delta, chain.getY(), chain.getWidth(), chain.getHeight(), this);

		for(int i=0; i<5; i++) {
			g.drawImage(falo, fireArea.getX()+i*200+delta, fireArea.getY(), 150, 200, this);
		}

		for(Breakable b : listaOggettiBreakable) {
			g.drawImage(i_cassa, b.getX()+delta, b.getY(), b.getWidth(), b.getHeight(), this);
			g.drawImage(green, b.getX()+delta, b.getY()-60, (int)(b.getLife()*100/b.getLifeMax()+2), 15, this);
			g.drawImage(red, b.getX()+delta+100, b.getY()-60, (int) (-100+b.getLife()*100/b.getLifeMax()), 15, this);
		}

		if(Rhode.getLife()>0) {
			switch(Rhode.getStato()) {
			case FERMOD : g.drawImage(r_stai_d, Rhode.getX()+delta-65, Rhode.getY()-40, 260, 200, this); break;
			case FERMOS : g.drawImage(r_stai_s, Rhode.getX()+delta-65, Rhode.getY()-40, 260, 200, this); break;
			case ATTACCADX : g.drawImage(r_attacca_d, Rhode.getX()+delta-65, Rhode.getY()-40, 260, 200, this); break;
			case ATTACCASX : g.drawImage(r_attacca_s, Rhode.getX()+delta-65, Rhode.getY()-40, 260, 200, this); break;
			case CAMMINADX : g.drawImage(r_corri_d, Rhode.getX()+delta-65, Rhode.getY()-40, 260, 200, this); break;
			case CAMMINASX : g.drawImage(r_corri_s, Rhode.getX()+delta-65, Rhode.getY()-40, 260, 200, this); break;
			case SALTAD : break;
			case SALTAS : break;
			}

			g.drawImage(green, Rhode.getX()+delta, Rhode.getY()-60, (int)(Rhode.getLife()*100/Rhode.getLifeMax()+2), 15, this);
			g.drawImage(red, Rhode.getX()+delta+100, Rhode.getY()-60, (int) (Rhode.getLife()*100/Rhode.getLifeMax()-100), 15, this);

			if(Rhode.getSfera().getVisible()) {
				g.drawImage(ball,Rhode.getSfera().getX()+delta,Rhode.sfera.getY(),70,70,this);
			}
			if(Rhode.getCarica()) {
				g.drawImage(ball,Rhode.getX()+delta+15,Rhode.getY()+10,70,70,this);
				g.drawString(""+(Rhode.getSfera().getPotenza()+Rhode.getSfera().getBonus()), Rhode.getX()+delta, Rhode.getY()-50);
			}
		}
		else
			g.drawImage(death, Rhode.getX()+delta, Rhode.getY(), Rhode.getWidth(), Rhode.getHeight(), this);

		if(completeDrago && completeAngel)
			g.drawImage(levelComplete, 0, 100, Window.SCREEN_WIDTH, 400, this);

		
	}

}











