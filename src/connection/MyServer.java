package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import graphic.Arena;
import logic.Character.azione;
import logic.Enemy;
import logic.Entity.direzione;

public class MyServer extends Thread{

	Arena arena;
	
	Socket incoming;
	
	BufferedReader in;
	
	boolean done;
	

	public MyServer(Arena arena, Socket incoming, BufferedReader in) {

		this.arena = arena;
		this.incoming = incoming;
		this.in= in;

	}

	@Override
	public void run() {
		super.run();

		try {
			while(!done) {
				String line = in.readLine();

				switch(line) {
				case("l") : {
					arena.Fabio.setStato(azione.CAMMINADX);
					arena.Fabio.setDirezione(direzione.DESTRA); 
					break;
				}
				case("j") : {
					arena.Fabio.setStato(azione.CAMMINASX);
					arena.Fabio.setDirezione(direzione.SINISTRA);
					break;
				}
				case("i") : {
					if(arena.Fabio.collision(arena.floor)) { 
						if(arena.Fabio.getDirezione()==direzione.GIU) {
							arena.Fabio.setDirezione(direzione.SU);
						}
						arena.Fabio.resetCont();
					}
					break;
				}
				case("n") : {
					arena.Fabio.setStato(azione.ATTACCASX);
					for(Enemy enemy : arena.listaNemici) {
						if(enemy.collision(arena.Fabio) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					break;
				}
				case("m") : {
					arena.Fabio.setStato(azione.ATTACCADX);
					for(Enemy enemy : arena.listaNemici) {
						if(enemy.collision(arena.Fabio) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					break;
				} 
				case("o") : {
					if(arena.Fabio.getPower()>300) {
						arena.Fabio.getSfera().setVisible(false);
						if(!arena.Fabio.getCarica())
							arena.Fabio.getSfera().ripristinaBonus();
						arena.Fabio.setCarica(true);
					}
					break;
				} 
				case("p") : {
					if(arena.Fabio.getPower()>300) {
						arena.Fabio.getSfera().setVisible(false);
						if(!arena.Fabio.getCarica())
							arena.Fabio.getSfera().ripristinaBonus();
						arena.Fabio.setCarica(true);
					}
					break;
				}

				case("J") :{
					arena.Fabio.setStato(azione.FERMOS);
					arena.Fabio.setDirezione(direzione.GIU);
					break;
				}
				case("L") : {
					arena.Fabio.setStato(azione.FERMOD); 
					arena.Fabio.setDirezione(direzione.GIU);
					break;
				}
				case("N") : {
					arena.Fabio.setStato(azione.FERMOS); 
					break;
				}
				case("M") : {
					arena.Fabio.setStato(azione.FERMOD); 
					break;
				}

				case("O") : { 
					if(arena.Fabio.getCarica()) {
						arena.Fabio.sfera.setX(arena.Fabio.getX()+101);
						arena.Fabio.sfera.setY(arena.Fabio.getY()+10);
						arena.Fabio.sfera.setDirezione(direzione.DESTRA);
						arena.Fabio.sfera.setVisible(true);
						arena.Fabio.setCarica(false);
						arena.Fabio.resetPower();
					}
					break;
				}
				case("P") : {
					if(arena.Fabio.getCarica()) {
						arena.Fabio.sfera.setX(arena.Fabio.getX()-1);
						arena.Fabio.sfera.setY(arena.Fabio.getY()+10);
						arena.Fabio.sfera.setDirezione(direzione.SINISTRA);
						arena.Fabio.sfera.setVisible(true);
						arena.Fabio.setCarica(false);
						arena.Fabio.resetPower();
					}
					break;
				} 
				}

				if(line.equals("addio"))
					done = true;
			}
			incoming.close();
		}
		catch (Exception e) {
			try {
				incoming.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
