package logic;

public class World {
	
	int dimX, dimY;

	public World() {
		dimX = 0;
		dimY = 0;
	}
	
	public World(int x, int y) { 
		dimX = x;
		dimY = y;
	}
	
	public int getdimX() { return dimX; }
	public int getdimY() { return dimY; }

}
