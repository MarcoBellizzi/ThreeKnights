package graphic;

public class MyButton {
	private int x, y, width, height;
	public int id=0;
	public boolean changeSize = true;

	
	public MyButton() {
		x = 0;
		y = 0;
		width = 10;
		height = 10;
	}

	public MyButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public MyButton(MyButton b) {
		this.x = b.x;
		this.y = b.y;
		this.width = b.width;
		this.height = b.height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if (changeSize)
			this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if (changeSize)
			this.height = height;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isOver(int mouseX,int mouseY){
		if(mouseX >= x
				&& mouseX <= x + width
				&& mouseY >= y
				&& mouseY <= y + height)
			return true;
		return false;
	}

}
