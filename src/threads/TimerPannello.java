package threads;

import javax.swing.JPanel;

public class TimerPannello extends Thread {

	JPanel p;
	
	public TimerPannello(JPanel p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		super.run();
		
		while(true) {
			p.repaint();
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
