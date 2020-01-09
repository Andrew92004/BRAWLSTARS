
public class Crates {

int x;
int y;
int width;
int height;

public Crates(int paramX, int paramY){
x = paramX;
y = paramY;	
width = 16;
height = 16;
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

public int getWidth() {
	return width;
}

public void setWidth(int width) {
	this.width = width;
}

public int getHieght() {
	return height;
}

public void setHieght(int hieght) {
	this.height = hieght;
}

public void setY(int y) {
	this.y = y;
}
//Rectangle r = new Rectangle(x, y, width, height);

}
