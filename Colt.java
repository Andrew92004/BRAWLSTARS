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
		maxHP = 5040;
		HP = maxHP;
		scale = 2;
		img = getImage("colt.png");
		width = 128;
		height = 128;
		//range = 7
		init(p[0],p[1]);
	}
	
	
	public void shoot(ArrayList<Bullet> bullets){
		ammo--;
		reload = reloadSpeed;
		inCombat = true;
		shotTimer = 18;
	}
	public void update(int fps, ArrayList<Bullet> bullets){
		if (reload>0){
			reload-=1/fps;
			if (reload==0){
				ammo++;
				if (ammo != 3) reload=reloadSpeed;
			}
		}
		
		if (HP<=0){
			HP = maxHP;
			x = xi;
			y = yi;
			ammo = 3;
			init(x,y);
		}
		shotPattern(bullets);

	}
	public void shotPattern(ArrayList<Bullet> bullets){
		if (shotTimer<=0) return;
		if (shotTimer%3==0){
			for (int i = 0; i < 5; i++){
				bullets.add(new Bullet(team,x+64,y+60,18,theta,140,2,0));
			}
		}
		shotTimer --;
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
