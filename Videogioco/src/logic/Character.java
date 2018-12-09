package logic;

public class Character extends Entity {

	public enum azione { FERMOD, FERMOS , CAMMINADX , CAMMINASX , ATTACCADX , ATTACCASX , SALTAD , SALTAS };
	
	public azione stato;
	public double life;
	public double lifeMax;
	public Gun sfera;
	public int cont;

	public Character(){
		super();
		stato = azione.FERMOD;
		life = 0.0;
		lifeMax = 0.0;
		sfera = new Gun();
		cont = 0;
	}

	public double getLife() { return this.life; }
	public void reduceLife(double x) { this.life -= x; } 
	
	public double getLifeMax() { return this.lifeMax; }

	public azione getStato() { return stato; }
	public void setStato(azione a) { this.stato = a; }
	
	public Gun getSfera() { return sfera; }
	

	public int getCont() { return cont; }  
	public void increaseCont() { cont++; }
	public void resetCont() { cont = 0; }
	
}
