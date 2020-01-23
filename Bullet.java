import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Bullet {
	private int x, y;
	private int vx, vy, vel;
	private double theta;
	private int scale;
	public Bullet(String team, int startX, int startY, int v, double angle){
		
		x = startX;
		y = startY;
		theta = 0;
		vel = v;
		//range = 7
		scale = 4;
		System.out.println(theta);
	}
	
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getVx() {
		return vx;
	}


	public int getVy() {
		return vy;
	}


	public int getVel() {
		return vel;
	}


	public double getTheta() {
		return theta;
	}


	public int getScale() {
		return scale;
	}


	public void move(){
		vx= -(int)(vel*Math.sin(theta));
		vy = -(int)(vel*Math.cos(theta));
		x+=vx;
		y+=vy;
	}
}
