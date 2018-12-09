package graphic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import connection.MyClient;
import logic.Character.azione;
import logic.Enemy;
import logic.Entity.direzione;

public class ArenaClient extends Arena {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Socket client;
	public BufferedReader in;
	public PrintWriter out;
	public MyClient clientMarco;

	
	public ArenaClient() {
		super();
		initEH();
		connettiClient();
	}
	
	public void connettiClient() {
		
		try {
			client = new Socket("127.0.0.1",4444);

			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());

			clientMarco = new MyClient(this,client,in);
			clientMarco.start();
			timerArena.start();

		} catch(Exception e) {
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
					Fabio.setStato(azione.CAMMINADX);
					Fabio.setDirezione(direzione.DESTRA);
					out.println("l");
					out.flush();
					break;
				}
				case(KeyEvent.VK_LEFT) : {
					Fabio.setStato(azione.CAMMINASX);
					Fabio.setDirezione(direzione.SINISTRA);
					out.println("j");
					out.flush();
					break;
				}
				case(KeyEvent.VK_UP) : {
					if(Fabio.collision(floor)) { 
						if(Fabio.getDirezione()==direzione.GIU) {
							Fabio.setDirezione(direzione.SU);
						}
						Fabio.resetCont();
					}
					out.println("i");
					out.flush();
					break;
				}
				case(KeyEvent.VK_A) : {
					Fabio.setStato(azione.ATTACCASX);
					for(Enemy enemy : listaNemici) {
						if(enemy.collision(Fabio) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					out.println("n");
					out.flush();
					break;
				}
				case(KeyEvent.VK_S) : {
					Fabio.setStato(azione.ATTACCADX);
					for(Enemy enemy : listaNemici) {
						if(enemy.collision(Fabio) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					out.println("m");
					out.flush();
					break;
				} 
				case(KeyEvent.VK_X) : {
					if(Fabio.getPower()>300) {
						Fabio.getSfera().setVisible(false);
						if(!Fabio.getCarica())
							Fabio.getSfera().ripristinaBonus();
						Fabio.setCarica(true);
					}
					out.println("o");
					out.flush();
					break;
				} 
				case(KeyEvent.VK_Z) : {
					if(Fabio.getPower()>300) {
						Fabio.getSfera().setVisible(false);
						if(!Fabio.getCarica())
							Fabio.getSfera().ripristinaBonus();
						Fabio.setCarica(true);
					}
					out.println("p");
					out.flush();
					break;
				}
				case(KeyEvent.VK_P) : {
					try {
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Window.SCREEN_MANAGER = new Window();
					Window.SCREEN_MANAGER.setVisible(true);
				}
				
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				
				switch(e.getKeyCode()) {
				case(KeyEvent.VK_RIGHT) : {
					Fabio.setStato(azione.FERMOD);
					Fabio.setDirezione(direzione.GIU);
					out.println("L");
					out.flush();
					break;
				}
				case(KeyEvent.VK_LEFT) : {
					Fabio.setStato(azione.FERMOS); 
					Fabio.setDirezione(direzione.GIU);
					out.println("J");
					out.flush();
					break;
				}
				case(KeyEvent.VK_A) : {
					Fabio.setStato(azione.FERMOS); 
					out.println("N");
					out.flush();
					break;
				}
				case(KeyEvent.VK_S) : {
					Fabio.setStato(azione.FERMOD);
					out.println("M");
					out.flush();
					break;
				}

				case(KeyEvent.VK_X) : { 
					if(Fabio.getCarica()) {
						Fabio.sfera.setX(Fabio.getX()+101);
						Fabio.sfera.setY(Fabio.getY()+10);
						Fabio.sfera.setDirezione(direzione.DESTRA);
						Fabio.sfera.setVisible(true);
						Fabio.setCarica(false);
						Fabio.resetPower();
					}
					out.println("O");
					out.flush();
					break;
				}
				case(KeyEvent.VK_Z) : {
					if(Fabio.getCarica()) {
						Fabio.sfera.setX(Fabio.getX()-1);
						Fabio.sfera.setY(Fabio.getY()+10);
						Fabio.sfera.setDirezione(direzione.SINISTRA);
						Fabio.sfera.setVisible(true);
						Fabio.setCarica(false);
						Fabio.resetPower();
					}
					out.println("P");
					out.flush();
					break;
				} 
				}
			}

		});

	}

}
