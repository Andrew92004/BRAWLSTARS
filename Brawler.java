
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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
	
	protected int x, y, vx, vy;
	protected double theta;
	protected int width, height;
	protected double scale;
	
	public Brawler(int t, int[] p){
		team = t;
		x = p[0];
		y = p[1];
		ammo = 3;
		supCharge = 0;
	}
	
	public void heal(){
		HP+=maxHP/5;
	}
	
	public void takeDamage(int damage, String effect){
		inCombat = true;
		HP -= damage;
	}
	
	public void update(int fps){
		if (reload>0){
			reload-=1/fps;
			if (reload==0){
				ammo++;
				if (ammo != 3) reload=reloadSpeed;
			}
		}
	}
	
	public void shoot(){};
	
	public void controlMove(int rl, int ud){
		if (rl==37){
			vx = -3;
		}
		else if (rl==39){
			vx = 3;
		}
		else if (rl==-1) vx = 0;
		
		if (ud==38){
			vy = -3;
		}
		else if (ud==40){
			vy = 3;
		}
		else if (ud==-1) vy = 0;
	}
	public boolean collided(int ox, int oy, int ow, int oh) {
		Rectangle obs = new Rectangle(ox, oy, ow, oh);
		Rectangle brawler = new Rectangle(x, y, width, height);
		return obs.intersects(brawler);
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
