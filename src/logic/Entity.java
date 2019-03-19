package logic;

public class Entity extends World {

	public enum direzione { DESTRA , SINISTRA , SU , GIU }
	
	int x,y;
	int width, height;
	
	public direzione dir;
	
	public Entity() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void setWidth(int w) { width = w; }
	public void setHeight(int h) { height = h; }
	
	public void reduceHeight(int h) { height -= h; }
	
	public void setDirezione(direzione d) { dir = d; }
	public direzione getDirezione() { return dir; }

	public void sposta(int x, int y) {
		this.y += y;
		this.x += x;
		/*if(this.x+x >= 200 && this.x+x <= 15000) {
			this.x += x;  
		}
		else {
			if(this instanceof Gun) {
				((Gun) this).setVisible(false);
			}
		}*/
	}

	public boolean collision(Entity e) {
		if(this.getX() + this.getWidth() >= e.getX()  && this.getX() <= e.getX() + e.getWidth())
				if(this.getY() + this.getHeight() >= e.getY()  && this.getY() <= e.getY() + e.getHeight())
			return true;

		return false;
	}

}
