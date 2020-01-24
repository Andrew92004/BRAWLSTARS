import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Brawler {
	protected int team;
	protected double reloadSpeed;
	protected double reload;
	protected int maxCharge;
	protected int supCharge;
	protected int ammo;
	protected int maxHP;
	protected int HP;
	protected boolean inCombat;
	protected Image img;
	
	protected int x, y, vx, vy,vel;
	protected double theta;
	protected int width, height;
	protected double scale;
	
	public int xi, yi;
	
	public Brawler(int t, int[] p){
		team = t;
		x = p[0];
		y = p[1];
		xi = x;
		yi = y;
		ammo = 3;
		supCharge = 0;
		vel = 3;
	}
	
	public void heal(){
		HP+=maxHP/5;
	}
	
	public void takeDamage(int damage, int effect){
		inCombat = true;
		HP -= damage;
	}
	

	
	public void shoot(){};
	
	public void controlMove(int rl, int ud){
		if (rl==37){
			vx = -vel;
		}
		else if (rl==39){
			vx = vel;
		}
		else if (rl==-1) vx = 0;
		
		if (ud==38){
			vy = -vel;
		}
		else if (ud==40){
			vy = vel;
		}
		else if (ud==-1) vy = 0;
	}
	
	public double getAngle(int mx, int my){
		return Math.PI/2-Math.atan2(y-my+height/2, x-mx+width/2);
	}
	
	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}
	
}
