import java.awt.Graphics;
import java.awt.Image;


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
	protected int x, y;
	protected double vx, vy;
	protected double vel;
	protected double theta;
	protected int width, height;
	protected double scale;
	protected boolean showImage;
	protected int xi, yi;
	public Brawler(int t, int[] p){
		team = t;
		x = p[0];
		y = p[1];
		xi = x;
		yi = y;
		ammo = 3;
		supCharge = 0;
		vel = 3;
		showImage = true;
	}
	
	public void heal(){
		HP+=maxHP/5;
	}
	
	public void takeDamage(int damage, int effect){
		inCombat = true;
		HP -= damage;
		System.out.println("HP: " + HP);
		if(HP<= 0) {
			showImage = false;
		}
	}
	
	
	public void constrainMove(Crate[] crates) {
		for (int i = 0; i < crates.length; i++) {
			if (crates[i]==null) continue;
			Crate c = crates[i];
			if (x+128>c.getX()+5&&x+5<c.getX()+64) {
				if (y + 128 >= c.getY()&&y <= c.getY()+32&&vy > 0) {
					vy = 0;
				}
				if (y + 64 >= c.getY()+32&&y <= c.getY()+64&&vy < 0) {
					vy = 0;
				}
			}
			if (y+128>c.getY()+5&&y+5<c.getY()+64) {
				if (x + 128 >= c.getX()&&x <= c.getX()+32&&vx > 0) {
					vx = 0;
				}
				if (x + 64 >= c.getX()+32&&x <= c.getX()+64&&vx < 0) {
					vx = 0;
				}
			}
		}
		
		if (x<0&&vx<0) {
			vx = 0;
		}
		if (x>1200&&vx>0) {
			vx = 0;
		}
		if (y<-64&&vy<0) {
			vy = 0;
		}
		if (y+64>1600&&vy>0) {
			vy = 0;
		}
	}
	
	
	
	public void shoot(){};
	
	public void controlMove(int rl, int ud){
		if (rl != -1) {
			if (rl == 2) vx = vel;
			if (rl == 1) vx = -vel;
			if (rl == 0) vx = 0;
		}
		if (ud != -1) {
			if (ud == 2) vy = vel;
			if (ud == 1) vy = -vel;
			if (ud == 0) vy = 0;
		}

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

	public void paint(Graphics g) {}
	
}
