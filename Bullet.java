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
	public int size;
	private int effect;
	public int team;
	public Bullet(int team, int startX, int startY, int v, double angle, int damage, int size, int effect){
		this.team = team;
		x = startX;
		y = startY;
		theta = 0;
		vel = v;
		//range = 7
		theta = angle;
		scale = 4;
		this.damage = damage;
		this.size = size;
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
		if (effect == 1){
			b.vel = 1;
		}
	}

	public void move(){
		vx= -(int)(vel*Math.sin(theta));
		vy = -(int)(vel*Math.cos(theta));
		x+=vx;
		y+=vy;
	}
	
	public boolean collided(int tarX, int tarY, int tarR) {
		return (tarX*tarX + tarY*tarY) < (tarR+size)*(tarR+size);
	}
}
