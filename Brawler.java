import java.awt.Image;


public class Brawler {
	public int team;
	public double reloadSpeed;
	public double reload;
	public int maxCharge;
	public int supCharge;
	public int ammo;
	public int maxHP;
	public int HP;
	public boolean inCombat;
	public Image img;
	public int x, y;
	public double vx, vy;
	public double vel;
	public double theta;
	public int width, height;
	public double scale;
	
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
		if (rl==65){
			vx = -vel;
		}
		else if (rl==68){
			vx = vel;
		}
		else if (rl==-1) vx = 0;

		if (ud==87){
			vy = -vel;
		}
		else if (ud==83){
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
