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
	private int damage;
	private int type;
	private int effect;
	public int team;
	public Bullet(int team, int startX, int startY, int v, double angle, int damage, int type, int effect){
		this.team = team;
		x = startX;
		y = startY;
		theta = 0;
		vel = v;
		//range = 7
		theta = angle;
		scale = 4;
		this.damage = damage;
		this.type = type;
		this.effect = effect;
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
	
	public void onHit(Brawler b){
		
	}

	public void move(){
		vx= -(int)(vel*Math.sin(theta));
		vy = -(int)(vel*Math.cos(theta));
		x+=vx;
		y+=vy;
	}
}
