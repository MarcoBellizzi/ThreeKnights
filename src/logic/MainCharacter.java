package logic;

public class MainCharacter extends Character {
	
	direzione dir;
	boolean caricando;
	int power;
	
	public MainCharacter() {
		super();
	}

	public MainCharacter(int x, int y, double life, int width, int height) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.lifeMax = life;
		this.width = width;
		this.height = height;
		this.dir = direzione.GIU;
		stato = azione.FERMOD;
		sfera = new Gun(100,70,200,60,null);
		sfera.setMySfera(true);
		caricando = false;
		power = 300;
	}
	
	public void setCarica(boolean c) { caricando = c; }
	public boolean getCarica() { return caricando; }
	
	public int getPower() { return power; }
	public void increasePower() { power++; }
	public void resetPower() { power = 0; }

	
	
}
