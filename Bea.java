import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;



public class Bea extends Brawler {
	double scale;
	
	public Bea(int t, int[] p) {
		super(t, p);
		maxCharge = 4620;
		reloadSpeed = 1.5;
		maxHP = 5040;
		HP = maxHP;
		scale = 2;
		img = getImage("bea.png");
		width = 128;
		height = 128;
		//range = 7
		init(p[0],p[1]);
	}
	
	
	public void shoot(ArrayList<Bullet> bullets){
		for (int i = 0; i < 5; i++){
			bullets.add(new Bullet("beabulletC.png",x,y,10,theta));
		}
		ammo--;
		reload = reloadSpeed;
		inCombat = true;
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
