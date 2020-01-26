import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;



public class Colt extends Brawler {
	double scale;
	int shotTimer;
	
	public Colt(int t, int[] p) {
		super(t, p);
		maxCharge = 4620;
		reloadSpeed = 1.5;
		maxHP = 3920;
		HP = maxHP;
		scale = 2;
		img = getImage("colt.png");
		width = 128;
		height = 128;
		//range = 7
		init(p[0],p[1]);
	}
	
	
	public void shoot(ArrayList<Bullet> bullets){
		if (ammo>0) {
			ammo--;
			reload = reloadSpeed;
			combatTimer = 3;
			shotTimer = 18;
		}
	}
	public void update(int fps, ArrayList<Bullet> bullets){
		if (reload>0){
			reload-=1/(double)fps;
			if (reload<=0){
				ammo++;
				if (ammo != 3) reload=reloadSpeed;
			}
		}
		if (ammo>3) ammo = 3;
		if (combatTimer>0){
			combatTimer-=1/(double)fps;
		}
		if (combatTimer<=0){
			heal();
		}
		
		if (HP<=0){
			HP = maxHP;
			x = xi;
			y = yi;
			ammo = 3;
			spin(0);
			init(x,y);
		}
		move();
		shotPattern(bullets);
	}
	public void shotPattern(ArrayList<Bullet> bullets){
		if (shotTimer<=0) return;
		if (shotTimer%3==0){
			for (int i = 0; i < 1; i++){
				bullets.add(new Bullet(team,x+64,y+60,15,theta,500,2,0));
			}
		}
		shotTimer --;
	}
	
	//bot
	public void runBot(ArrayList<Bullet> bullets, Brawler brl, Safe safe) {
		Brawler tar = safe;
		if ((x-brl.getX())*(x-brl.getX())+(y-brl.getY())*(y-brl.getY())<=500*500) {
			tar = brl;
		}
		if ((x-tar.getX())*(x-tar.getX())+(y-tar.getY())*(y-tar.getY())<=300*300) {
			spin(getAngle(tar.getX()+64,tar.getY()+64));
			if (ammo==3) {
			shoot(bullets);
			}
			return;
		}
		if (tar.getX()>x+64) controlMove(2,-1);
		else if (tar.getX()+64<x) controlMove(1,-1);
		else controlMove(0,-1);
		
		if (tar.getY()>y+64) controlMove(-1,2);
		else if (tar.getY()+64<x) controlMove(-1,1);
		else controlMove(-1,0);
	}
	
	//MOVEMENT
	
	public void move(){
		tx.translate(vy*Math.cos(Math.PI/2+theta)/scale,vy*Math.sin(Math.PI/2+theta)/scale);
		tx.translate(vx*Math.cos(theta)/scale,vx*Math.sin(theta)/scale);
		x+=vx;
		y+=vy;
	}
	
	public void spin(double a){
		//By @ArkyLi
		double oldangle = theta;
		theta=a;
		tx.rotate(oldangle-theta, width/2/scale, height/2/scale);
	}
	//DRAWING
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affinetransform
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
		}

		protected void init(double a, double b) {
			tx.setToTranslation(a, b);
			tx.scale(scale, scale);
		}

		// converts image to make it drawable in paint
		protected Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = Shelly.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return tempImage;
		}
	
}
