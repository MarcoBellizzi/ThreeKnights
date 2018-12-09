package logic;

public class DragoVerde extends Drago {

	public DragoVerde() {
		super();
	}
	
	public DragoVerde(int x, int y, double life, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.lifeMax = life;
		this.stato = azione.FERMOS;
		this.sfera = new Gun();
		this.width = width;
		this.height = height;
		this.speed = speed;
		cont = 0;
		attivo = false;
		alive = true;
	}
	
	public DragoVerde(int x, int y, double life, int width, int height, int speed, int speed2) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.lifeMax = life;
		this.stato = azione.FERMOS;
		this.sfera = new Gun();
		this.sfera.setVelocita(speed2);
		this.width = width;
		this.height = height;
		this.speed = speed;
		cont = 0;
		attivo = false;
		alive = true;
	}
}
