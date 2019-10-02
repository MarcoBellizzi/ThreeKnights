package connection;

import java.io.*;
import java.net.*;

import graphic.ArenaClient;
import logic.Enemy;
import logic.Character.azione;
import logic.Entity.direzione;

public class MyClient extends Thread {

	ArenaClient arenaClient;
	
	Socket client;
	
	BufferedReader in;	
	
	boolean done;
	
	
	public MyClient(ArenaClient arenaClient, Socket client, BufferedReader in) {
		this.arenaClient = arenaClient;
		this.client = client;
		this.in = in;
		done = false;
		
	}



	@Override
	public void run() {
		super.run();

		try {

			while(!done) {
				
				String line = in.readLine();

				switch(line) {
				case("g") : {
					arenaClient.Marco.setStato(azione.CAMMINADX);
					arenaClient.Marco.setDirezione(direzione.DESTRA); 
					break;
				}
				case("d") : {
					arenaClient.Marco.setStato(azione.CAMMINASX);
					arenaClient.Marco.setDirezione(direzione.SINISTRA);
					break;
				}
				case("r") : {
					if(arenaClient.Marco.collision(arenaClient.floor)) { 
						if(arenaClient.Marco.getDirezione()==direzione.GIU) {
							arenaClient.Marco.setDirezione(direzione.SU);
						}
						arenaClient.Marco.resetCont();
					}
					break;
				}
				case("q") : {
					arenaClient.Marco.setStato(azione.ATTACCASX);
					for(Enemy enemy : arenaClient.listaNemici) {
						if(enemy.collision(arenaClient.Marco) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					break;
				}
				case("w") : {
					arenaClient.Marco.setStato(azione.ATTACCADX);
					for(Enemy enemy : arenaClient.listaNemici) {
						if(enemy.collision(arenaClient.Marco) && enemy.getAttivo())
							enemy.reduceLife(40);
						if(enemy.getLife() <= 0)
							enemy.kill();
					}
					break;
				} 
				case("c") : {
					if(arenaClient.Marco.getPower()>300) {
						arenaClient.Marco.getSfera().setVisible(false);
						if(!arenaClient.Marco.getCarica())
							arenaClient.Marco.getSfera().ripristinaBonus();
						arenaClient.Marco.setCarica(true);
					}
					break;
				} 
				case("v") : {
					if(arenaClient.Fabio.getPower()>300) {
						arenaClient.Fabio.getSfera().setVisible(false);
						if(!arenaClient.Fabio.getCarica())
							arenaClient.Fabio.getSfera().ripristinaBonus();
						arenaClient.Fabio.setCarica(true);
					}
					break;
				}

				case("D") :{
					arenaClient.Marco.setStato(azione.FERMOS);
					arenaClient.Marco.setDirezione(direzione.GIU);
					break;
				}
				case("G") : {
					arenaClient.Marco.setStato(azione.FERMOD); 
					arenaClient.Marco.setDirezione(direzione.GIU);
					break;
				}
				case("Q") : {
					arenaClient.Marco.setStato(azione.FERMOS); 
					break;
				}
				case("W") : {
					arenaClient.Marco.setStato(azione.FERMOD); 
					break;
				}

				case("C") : { 
					if(arenaClient.Marco.getCarica()) {
						arenaClient.Marco.sfera.setX(arenaClient.Marco.getX()+101);
						arenaClient.Marco.sfera.setY(arenaClient.Marco.getY()+10);
						arenaClient.Marco.sfera.setDirezione(direzione.DESTRA);
						arenaClient.Marco.sfera.setVisible(true);
						arenaClient.Marco.setCarica(false);
						arenaClient.Marco.resetPower();
					}
					break;
				}
				case("V") : {
					if(arenaClient.Marco.getCarica()) {
						arenaClient.Marco.sfera.setX(arenaClient.Marco.getX()-1);
						arenaClient.Marco.sfera.setY(arenaClient.Marco.getY()+10);
						arenaClient.Marco.sfera.setDirezione(direzione.SINISTRA);
						arenaClient.Marco.sfera.setVisible(true);
						arenaClient.Marco.setCarica(false);
						arenaClient.Marco.resetPower();
					}
					break;
				} 
				}

				if(line.equals("addio"))
					done = true;
				
			}
			client.close();

		}
		catch (Exception e) {
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
