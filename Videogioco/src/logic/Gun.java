package logic;

public class Gun extends Weapon {
	
	boolean visible;
	boolean mySfera;
	int potenza;
	int velocita;
	int bonus;
	direzione dir;
	
	public Gun() {
		width = 100;
		height = 70;
		potenza = 200;
		velocita = 30;
		dir = direzione.DESTRA;
		visible = false;
		mySfera = false;
	}
	
	public Gun(int width, int height, int potenza, int velocita, direzione dir) {
		this.width = width;
		this.height = height;
		this.potenza = potenza;
		this.velocita = velocita;
		this.dir = dir;
		bonus = 0;
		visible = false;
		mySfera = false;
	}


	public boolean getVisible() { return visible; }
	public int getPotenza() { return potenza; }
	public int getVelocita() { return velocita; }
	public int getBonus() { return bonus; }
	
	public void setVisible(boolean b) { visible = b; }
	public void setMySfera(boolean b) { mySfera = b; }
	public void setVelocita(int vel) { this.velocita = vel; }
	
	public void aumentaBonus() { bonus += 40; }
	public void aumentaBonus(int bonus) { this.bonus += bonus; }
	public void ripristinaBonus() { bonus = 0; }
	
	public boolean isMySfera() { return mySfera; }
	
	
}



