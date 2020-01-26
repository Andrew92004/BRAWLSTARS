import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class Shelly extends Brawler {
	double scale;
	
	public Shelly(int t, int[] p) {
		super(t, p);
		maxCharge = 4620;
		reloadSpeed = 1.25;
		maxHP = 5040;
		HP = maxHP;
		scale = 2;
		img = getImage("shelly.png");
		width = 128*2/3;
		height = 256;
		//range = 7
		combatTimer =0;
		init(p[0],p[1]);
	}
	
	
	public void shoot(ArrayList<Bullet> bullets){
		if (ammo>0) {
			for (int i = 0; i < 5; i++){
				bullets.add(new Bullet(team,x+64,y+64,10,theta-Math.PI/8+(i*Math.PI/20),420,10,0));
			}
			ammo--;
			reload = reloadSpeed;
			combatTimer = 5;
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
	}
	
	public void runBot(ArrayList<Bullet> bullets, Brawler brl, Safe safe) {
		Brawler tar = brl;
		if ((x-safe.getX())*(x-safe.getX())+(y-safe.getY())*(y-safe.getY())<=300*300) {
			tar = safe;
		}
		if ((x-tar.getX())*(x-tar.getX())+(y-tar.getY())*(y-tar.getY())<=200*200) {
			spin(getAngle(tar.getX()+64,tar.getY()+64));
			if (ammo==3) {
			shoot(bullets);
			return;
			}
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
				//System.out.println(theta);
				double oldangle = theta;
				theta=a;
				tx.rotate(oldangle-theta, width/2/scale, height/3/scale);
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
