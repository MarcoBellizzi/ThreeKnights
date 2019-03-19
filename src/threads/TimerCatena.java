package threads;

import graphic.LivelloMarco;
import logic.StaticThing;

public class TimerCatena extends Thread {

	StaticThing catena;
	LivelloMarco p;
	
	public TimerCatena(StaticThing catena, LivelloMarco p) {
		this.catena = catena;
		this.p = p;
	}

	@Override
	public void run() {
		super.run();
		
		while(catena.getHeight() >= 0) {
				catena.reduceHeight(20);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
}
