package threads;

import graphic.LivelloMarco;
import logic.Angel;
import logic.Character.azione;
import logic.Entity.direzione;

public class TimerAngelo extends Thread {

	LivelloMarco p;
	boolean complete;

	public TimerAngelo(LivelloMarco l) {
		p = l;
		complete = false;
	}

	@Override
	public void run() {
		super.run();

		while(!complete) {

			complete = true;

			for(Angel angel : p.listaAngeli) {

				if(angel.isAlive())
					complete = false;

				if(angel.getAttivo()) {

					angel.increaseCont();

					if(angel.getCont() == 1) {
						angel.setStato(azione.FERMOD);
					}

					if(angel.getCont() == 20) {     // appare a sinistra
						angel.setX(p.Rhode.getX()-400);
						angel.setStato(azione.ATTACCADX);
					}

					if(angel.getCont() == 30) {   // attacca a destra
						angel.setStato(azione.FERMOD);
						angel.getSfera().setX(angel.getX()+angel.getWidth());
						angel.getSfera().setY(angel.getY()+100);
						angel.getSfera().setDirezione(direzione.DESTRA);
						angel.getSfera().setVisible(true);
					}

					if(angel.getCont() == 60) {    // appare a destra
						angel.setX(p.Rhode.getX()+400);
						angel.setStato(azione.ATTACCASX);
					}

					if(angel.getCont() == 70) {    // attacca a sinistra
						angel.setStato(azione.FERMOD);
						angel.getSfera().setX(angel.getX());
						angel.getSfera().setDirezione(direzione.SINISTRA);
						angel.getSfera().setVisible(true);
					}

					if(angel.getCont() == 100) {    // vola
						angel.setStato(azione.SALTAD);
					}

					if(angel.getCont() >= 100 && angel.getCont() <= 130) {
						angel.sposta(0, -10);
					}

					if(angel.getCont() == 150) {     //  pioggia di meteoriti
						angel.getMeteora1().setX(angel.getX()-200);
						angel.getMeteora1().setY(-angel.getMeteora1().getHeight());
						angel.getMeteora1().setDirezione(direzione.GIU);
						angel.getMeteora1().setVisible(true);

						angel.getMeteora2().setX(p.Rhode.getX());
						angel.getMeteora2().setY(-angel.getMeteora2().getHeight());
						angel.getMeteora2().setDirezione(direzione.GIU);
						angel.getMeteora2().setVisible(true);

					}

					if(angel.getCont() >= 190 && angel.getCont() <=220) {   //  scende
						angel.sposta(0, 10);
					}

					if(angel.getCont() == 221) {
						angel.resetCont();
					}

				}
			}
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		p.completeAngel = true;
		
	}
}
















