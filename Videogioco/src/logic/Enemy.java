package logic;

public class Enemy extends Character {
	
	boolean attivo;
	boolean alive;
	
	public Enemy() {
		super();
	}
	
	public Enemy(int x, int y, double life, int width, int height) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.lifeMax = life;
		this.stato = azione.FERMOS;
		this.sfera = new Gun();
		this.width = width;
		this.height = height;
	}
	
	public boolean getAttivo() { return attivo; }
	public void setAttivo(boolean b) { attivo = b; }
	
	public boolean isAlive() { return alive; }
	public void kill() { 
		alive = false;
		attivo = false;
	}
	
}
