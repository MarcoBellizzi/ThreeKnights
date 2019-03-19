package logic;

public class Breakable extends DynamicThing {
 
	public double life;
	public double lifeMax;
	
	public Breakable(int x, int y, int w, int h,int l) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.life = l;
		this.lifeMax = l;
	}

	public double getLife() { return life; }
	public double getLifeMax() { return lifeMax; }
	
	public void reduceLife(double x) { life -= x; }
	
	
}
