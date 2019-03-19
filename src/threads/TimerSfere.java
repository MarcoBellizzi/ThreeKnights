package threads;

import graphic.LivelloMarco;
import logic.Breakable;
import logic.Enemy;
import logic.Gun;


public class TimerSfere extends Thread {

	LivelloMarco p;
	Breakable cassaToRemove;
	
	public TimerSfere(LivelloMarco p) { 
		this.p = p;
	}



	@Override
	public void run() {
		super.run();

		while(true) {

			cassaToRemove = null;

			synchronized(p.listaSfere) {
				for(Gun sfera : p.listaSfere) {
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
							synchronized (p.listaNemici) {
								for(Enemy enemy : p.listaNemici) {
									if(sfera.collision(enemy) && enemy.isAlive()) {
										enemy.reduceLife(sfera.getPotenza()+sfera.getBonus());
										if(enemy.getLife() <= 0) 
											enemy.kill();
										sfera.setVisible(false);
									}
								}
							}

							synchronized (p.listaOggettiBreakable) {
								for(Breakable cassa : p.listaOggettiBreakable) {
									if(sfera.collision(cassa)) {
										cassa.reduceLife(sfera.getPotenza()+sfera.getBonus());
										if(cassa.getLife()<=0) {
											if(cassa.getX()==p.cassa2.getX()) {
												if(!p.timerCatena.isAlive())
													p.timerCatena.start();
											}
											cassaToRemove = cassa;
										}
										sfera.setVisible(false);
									}
								}
								p.listaOggettiBreakable.remove(cassaToRemove);
							}
						}
						else {
							if(sfera.collision(p.Rhode)) {
								p.Rhode.reduceLife(sfera.getPotenza());
								sfera.setVisible(false);
							}
						}
					}
					if(sfera.isMySfera()) {
						if(p.Rhode.getCarica()) {
							sfera.aumentaBonus();
						}
					}

					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}



