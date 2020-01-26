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
	}
	
	public void heal(){
		HP+=maxHP/5;
	}
	
	public void takeDamage(int damage, int effect){
		inCombat = true;
		HP -= damage;
		System.out.println("HP: " + HP);
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
	
}
