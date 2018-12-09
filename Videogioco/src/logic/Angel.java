package logic;

public class Angel extends Enemy {

	Gun meteora1;
	Gun meteora2;
	
	int speed;
	
	public Angel(int x, int y, double life, int width, int height) {
		super(x,y,life,width,height);
		this.getSfera().setWidth(70);
		meteora1 = new Gun(300,300,400,25,direzione.GIU);
		meteora2 = new Gun(200,200,800,40,direzione.GIU);
		cont = 0;
		attivo = false;
		alive = true;
		speed = 0;
	}
	
	public Angel(int x, int y, double life, int width, int height, int speed) {
		super(x,y,life,width,height);
		this.getSfera().setWidth(70);
		meteora1 = new Gun(300,300,400,25,direzione.GIU);
		meteora2 = new Gun(200,200,800,40,direzione.GIU);
		cont = 0;
		attivo = false;
		alive = true;
		this.speed = speed;
	}
	
	public Gun getMeteora1() { return meteora1; }
	public Gun getMeteora2() { return meteora2; }
	
	public int getSpeed() { return speed; }
	
	
	
}
