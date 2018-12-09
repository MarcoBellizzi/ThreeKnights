package threads;

import graphic.LivelloMarco;
import graphic.Window;
import logic.Angel;
import logic.Breakable;
import logic.Drago;
import logic.StaticThing;

public class TimerSpostamento extends Thread {

	LivelloMarco p;
	int altezzaPrec;

	public TimerSpostamento(LivelloMarco p) {
		this.p = p;
	}

	@Override
	public void run() {
		
		super.run();

		while(p.Rhode.getLife()>0) {
			
			p.Rhode.increaseCont();
			p.Rhode.increasePower();
			
			altezzaPrec = p.Rhode.getY() + p.Rhode.getHeight();
			
			if(p.Rhode.getCont()<=40) {
				switch(p.Rhode.getDirezione()) {
				case DESTRA : {
					p.Rhode.sposta(5, -10);
					break;
				}
				case SINISTRA : {
					p.Rhode.sposta(-5, -10);
					break;
				}
				case SU : {
					p.Rhode.sposta(0, -10);
					break;
				}
				case GIU : { break; }
				
				}
			}
			else {
				switch(p.Rhode.getDirezione()) {
				case DESTRA : {
					p.Rhode.sposta(5, 10);
					break;
				}
				case SINISTRA : {
					p.Rhode.sposta(-5, 10);
					break;
				}
				case SU : {
					p.Rhode.sposta(0, 10);
					break;
				}
				case GIU : {
					p.Rhode.sposta(0, 10);
					break;
				}
				}
			}
			
			if(p.Rhode.collision(p.beach)) {
				p.Rhode.setY(p.beach.getY()-p.Rhode.getHeight());
			}
			
			
			for(StaticThing o : p.listaOggettiStatici) {
				if(p.Rhode.collision(o)) {
					if(altezzaPrec <= o.getY()) {
						p.Rhode.setY(o.getY()-p.Rhode.getHeight());
					}
					else {					
						if(altezzaPrec-p.Rhode.getHeight() >= o.getY()+o.getHeight()) {
							p.Rhode.setY(o.getY()+o.getHeight()+1);
						}
						else {
							if(p.Rhode.getX() < o.getX()) {
								p.Rhode.setX(o.getX()-p.Rhode.getWidth());
							}
							else {
								p.Rhode.setX(o.getX()+o.getWidth());

							}
						}
					} 
				}
			}

			for(Breakable b : p.listaOggettiBreakable) {
				if(p.Rhode.collision(b)) {
					if(altezzaPrec <= b.getY()) {
						p.Rhode.setY(b.getY()-p.Rhode.getHeight());
					}
					else {					
						if(altezzaPrec-p.Rhode.getHeight() >= b.getY()+b.getHeight()) {
							p.Rhode.setY(b.getY()+b.getHeight());
						}
						else {
							if(p.Rhode.getX() < b.getX()) {
								p.Rhode.setX(b.getX()-p.Rhode.getWidth());
							}
							else {
								p.Rhode.setX(b.getX()+b.getWidth());

							}
						}
					} 
				}
			}

			
			if(p.Rhode.collision(p.chain)) {
				if(altezzaPrec <= p.chain.getY()) {
					p.Rhode.setY(p.chain.getY()-p.Rhode.getHeight());
				}
				else {					
					if(altezzaPrec-p.Rhode.getHeight() >= p.chain.getY()+p.chain.getHeight()) {
						p.Rhode.setY(p.chain.getY()+p.chain.getHeight());
					}
					else {
						if(p.Rhode.getX() < p.chain.getX()) {
							p.Rhode.setX(p.chain.getX()-p.Rhode.getWidth());
						}
						else {
							p.Rhode.setX(p.chain.getX()+p.chain.getWidth());

						}
					}
				} 
			}
			
			if(p.Rhode.collision(p.fireArea)) {
				p.Rhode.reduceLife(2);
			}
			
			
			//synchronized(p.listaDraghi) {
				for( Drago drago : p.listaDraghi) {
					if(p.Rhode.getX() > drago.getX()-500 && drago.isAlive()) {
						drago.setAttivo(true);
					}
				}
			//}

			//synchronized(p.listaAngeli) {
				for( Angel angel : p.listaAngeli) {
					if(p.Rhode.getX() > angel.getX() - 500 && angel.isAlive()) {
						angel.setAttivo(true);
					}
				}
		    //}
			
			
			
			if(p.Rhode.getX()+p.delta > Window.SCREEN_WIDTH/2)   //da capire && x>const
				p.delta-=5;
			if(p.Rhode.getX()+p.delta < Window.SCREEN_WIDTH/4)
				p.delta+=5;
			
			try {
				sleep(10); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}


