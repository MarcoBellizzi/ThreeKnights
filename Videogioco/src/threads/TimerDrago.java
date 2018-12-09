package threads;

import logic.Character.azione;
import logic.Drago;
import logic.DragoVerde;
import graphic.LivelloMarco;
import logic.Entity.direzione;


public class TimerDrago extends Thread {

	LivelloMarco p;

	int cont;
	
	boolean complete;

	public TimerDrago(LivelloMarco p) {
		this.p = p;
		complete = false;
	}



	@Override
	public void run() {
		super.run();

		while(!complete) {

			complete = true;
			
			synchronized (p.listaDraghi) {
				for(Drago drago : p.listaDraghi) {

					if(drago.isAlive()) {
						complete = false;
					}
					
					if(drago.getAttivo()) {

						drago.increaseCont();

						if(drago instanceof DragoVerde) {
							if(drago.collision(p.Rhode)) {
								p.Rhode.reduceLife(20);
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
								if(p.Rhode.getX()>=drago.getX()) {
									drago.setStato(azione.ATTACCADX);
									drago.getSfera().setDirezione(direzione.DESTRA);
								}
								else {
									drago.setStato(azione.ATTACCASX);
									drago.getSfera().setDirezione(direzione.SINISTRA);
								}
							}

							if(cont==90) {      //parte fuoco
								if(p.Rhode.getX()>=drago.getX())
									drago.getSfera().setX(drago.getX()+380);
								else
									drago.getSfera().setX(drago.getX());
								drago.getSfera().setY(drago.getY()+300);
								drago.getSfera().setVisible(true);
							}

							if(cont==94 ) {     // si guarda intorno
								if(p.Rhode.getX()>=drago.getX()) 
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
								if(p.Rhode.getX()>=drago.getX()) {
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
			}

			try {
				sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		p.completeDrago = true;

	}
}




