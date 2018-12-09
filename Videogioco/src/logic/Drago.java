package logic;

public class Drago extends Enemy {
	
	int speed;
	
	public Drago() {
		super();
	}
	
	public Drago(int x, int y, double life, int width, int height, int speed) {
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

	public Drago(int x, int y, double life, int width, int height, int speed,int speed2) {
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
	
	public int getSpeed() { return speed; }
	

}
