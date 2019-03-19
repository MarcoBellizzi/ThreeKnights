package threads;

import graphic.*;
import logic.*;
import logic.Character.azione;
import logic.Entity.direzione;

public class TimerArena extends Thread {

	Arena arena;

	int cont;
	
	boolean round2;
	boolean round3;

	public TimerArena(Arena arena) {
		this.arena = arena;
		round2 = false;
		round3 = false;
	}


	@Override
	public void run() {
		super.run();

		while(true) {

			for( MainCharacter character : arena.listaMainCharacter ) {

				if(character.getLife()>0) {

					character.increaseCont();
					character.increasePower();

					if(character.getCont()<=40) {
						switch(character.getDirezione()) {
						case DESTRA : {
							character.sposta(5, -10);
							break;
						}
						case SINISTRA : {
							character.sposta(-5, -10);
							break;
						}
						case SU : {
							character.sposta(0, -10);
							break;
						}
						case GIU : { break; }

						}
					}
					else {
						switch(character.getDirezione()) {
						case DESTRA : {
							character.sposta(5, 10);
							break;
						}
						case SINISTRA : {
							character.sposta(-5, 10);
							break;
						}
						case SU : {
							character.sposta(0, 10);
							break;
						}
						case GIU : {
							character.sposta(0, 10);
							break;
						}
						}
					}
				}

				for(StaticThing o : arena.listaStaticThing) {
					if(character.collision(o)) {
						if(character.getY() < o.getY()) {
							character.setY(o.getY()-character.getHeight());
						}
						else {
							if(character.getX() < o.getX()) {
								character.setX(o.getX()-character.getWidth());
							}
							else {
								character.setX(o.getX()+o.getWidth());

							}
						}
					} 
				}
			}


			for(Drago drago : arena.listaDraghi) {

				if(drago.getAttivo()) {

					drago.increaseCont();

					if(drago instanceof DragoVerde) {
						if(drago.collision(arena.Marco)) {
							arena.Marco.reduceLife(5);
						}
						if(drago.collision(arena.Fabio)) {
							arena.Fabio.reduceLife(5);
						}
					}


					if(drago.getCont() % drago.getSpeed() == 0 ) {

						cont = drago.getCont() / drago.getSpeed();

						if(cont ==1) {
							drago.setStato(azione.FERMOD);
						}
						if(cont ==19) {
							drago.setStato(azione.CAMMINADX);
						}
						if(cont>=20 && cont<40) {
							drago.sposta(10,0);
						}
						if(cont ==40) {
							drago.setStato(azione.FERMOS);
						}
						if(cont==59) {
							drago.setStato(azione.CAMMINASX);
						}
						if(cont>=60 && cont<80) {
							drago.sposta(-10,0);
						}


						if(cont==80) {         //inizia gif attacco
							if(arena.Marco.getX()>=drago.getX()) {
								drago.setStato(azione.ATTACCADX);
								drago.getSfera().setDirezione(direzione.DESTRA);
							}
							else {
								drago.setStato(azione.ATTACCASX);
								drago.getSfera().setDirezione(direzione.SINISTRA);
							}
						}

						if(cont==90) {      //parte fuoco
							if(arena.Marco.getX()>=drago.getX())
								drago.getSfera().setX(drago.getX()+380);
							else
								drago.getSfera().setX(drago.getX());
							drago.getSfera().setY(drago.getY()+300);
							drago.getSfera().setVisible(true);
						}

						if(cont==94 ) {     // si guarda intorno
							if(arena.Marco.getX()>=drago.getX()) 
								drago.setStato(azione.SALTAD);
							else
								drago.setStato(azione.SALTAS);
						}

						if(cont>94 && cont<119) {      //vola 
							if(drago.getStato() == azione.SALTAD)
								drago.sposta(10, -10);
							else
								drago.sposta(-10, -10);
						}


						if(cont==119) {       // si guarda intorno
							if(arena.Marco.getX()>=drago.getX()) {
								drago.setStato(azione.SALTAD);
							}
							else {
								drago.setStato(azione.SALTAS);
							}
						}

						if(cont>119 && cont<144) {       //scende 
							if(drago.getStato() == azione.SALTAD)
								drago.sposta(10, 10);
							else
								drago.sposta(-10, 10);
						}

						if(cont==144) {  
							drago.resetCont();
							drago.setStato(azione.FERMOD);
						}

					}

					if(drago.getLife()<0) {
						drago.kill();
					}
				}

			}

			for(Angel angel : arena.listaAngeli) {

				if(angel.getAttivo()) {

					angel.increaseCont();

					if(angel.getCont()%angel.getSpeed()==0) {

						cont = angel.getCont()/angel.getSpeed();
						
						if(cont == 1) {
							angel.setStato(azione.FERMOD);
						}

						if(cont == 20) {
							if(angel.getSpeed()==5)
								angel.setX(arena.Marco.getX()-400);
							else
								angel.setX(arena.Fabio.getX()-400);
							angel.setStato(azione.ATTACCADX);
						}

						if(cont == 30) {
							angel.setStato(azione.FERMOD);
							angel.getSfera().setX(angel.getX()+angel.getWidth());
							angel.getSfera().setY(angel.getY()+100);
							angel.getSfera().setDirezione(direzione.DESTRA);
							angel.getSfera().setVisible(true);
						}

						if(cont == 60) {
							if(angel.getSpeed()==5)
								angel.setX(arena.Fabio.getX()+400);
							else
								angel.setX(arena.Marco.getX()+400);
							angel.setStato(azione.ATTACCASX);
						}

						if(cont == 70) {
							angel.setStato(azione.FERMOD);
							angel.getSfera().setX(angel.getX());
							angel.getSfera().setDirezione(direzione.SINISTRA);
							angel.getSfera().setVisible(true);
						}

						if(cont == 100) {
							angel.setStato(azione.SALTAD);
						}

						if(cont >= 100 && cont <= 130) {
							angel.sposta(0, -10);
						}

						if(cont  == 150) {
							angel.getMeteora1().setX(arena.Fabio.getX());
							angel.getMeteora1().setY(-angel.getMeteora1().getHeight());
							angel.getMeteora1().setDirezione(direzione.GIU);
							angel.getMeteora1().setVisible(true);

							angel.getMeteora2().setX(arena.Marco.getX());
							angel.getMeteora2().setY(-angel.getMeteora2().getHeight());
							angel.getMeteora2().setDirezione(direzione.GIU);
							angel.getMeteora2().setVisible(true);

						}

						if(cont >= 190 && cont <=220) {
							angel.sposta(0, 10);
						}

						if(cont == 221) {
							angel.resetCont();
						}
					}
				}
			}


			for(Gun sfera : arena.listaSfere) {
				if(sfera.getVisible()) {
					switch(sfera.getDirezione()) {
					case DESTRA : {
						sfera.sposta(sfera.getVelocita(),0);   
						break;
					}
					case SINISTRA : {
						sfera.sposta(-sfera.getVelocita(),0);
						break;
					}
					case GIU : {
						sfera.sposta(0,sfera.getVelocita());
						break;
					}
					case SU : {
						sfera.sposta(0,-sfera.getVelocita());
						break;
					}
					}

					if(sfera.isMySfera()) {
						synchronized (arena.listaNemici) {
							for(Enemy enemy : arena.listaNemici) {
								if(sfera.collision(enemy) && enemy.getAttivo()) {
									enemy.reduceLife(sfera.getPotenza()+sfera.getBonus());
									if(enemy.getLife() <= 0) 
										enemy.kill();
									sfera.setVisible(false);
								}
							}
						}
					}
					else {
						for( MainCharacter character : arena.listaMainCharacter) {
							if(sfera.collision(character)) {
								character.reduceLife(sfera.getPotenza());
								sfera.setVisible(false);
							}
						}
					}
					if(sfera.collision(arena.wall1) || sfera.collision(arena.wall2)) {
						sfera.setVisible(false);
					}
				}
				if(sfera.isMySfera()) {
					for( MainCharacter character : arena.listaMainCharacter) {
						if(character.getCarica()) {
							sfera.aumentaBonus(3);
						}
					}
				}
			}
			
			if(!(arena.drago1.isAlive()) && !round2 ) {
				round2 = true;
				arena.drago2.setAttivo(true);
				arena.drago3.setAttivo(true);
				arena.dragoVerde1.setAttivo(true);
			}
			
			if( (!arena.drago2.isAlive()) && (!arena.drago3.isAlive()) && (!arena.dragoVerde1.isAlive()) && (!round3) )  {
				round3 = true;
				arena.drago4.setAttivo(true);
				arena.dragoVerde2.setAttivo(true);
				arena.dragoVerde3.setAttivo(true);
				arena.angel1.setAttivo(true);
				arena.angel2.setAttivo(true);
			}
			
			if( (!arena.drago4.isAlive()) && (!arena.dragoVerde2.isAlive()) && (!arena.dragoVerde3.isAlive())
					&& (!arena.angel1.isAlive()) && (!arena.angel2.isAlive()) ) {
				arena.complete = true;
			}
			
			if(!(arena instanceof ArenaClient)) {
				if(arena.Marco.getX()+arena.delta > Window.SCREEN_WIDTH/4 && arena.Marco.getX()<2300)
					arena.delta-=5;
				if(arena.Marco.getX()+arena.delta < Window.SCREEN_WIDTH/2 && arena.Marco.getX()>500)
					arena.delta+=5;
			}
			else {
				if(arena.Fabio.getX()+arena.delta > Window.SCREEN_WIDTH/4 && arena.Fabio.getX()<2300)
					arena.delta-=5;
				if(arena.Fabio.getX()+arena.delta < Window.SCREEN_WIDTH/2 && arena.Fabio.getX()>500)
					arena.delta+=5;
			}




			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
