import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Bea extends Brawler {
	double scale;
	boolean charged;
	int superCharge;
	boolean beaSuper;

	public Bea(int t, int[] p) {
		super(t, p);
		beaSuper = false;
		superCharge = 0;
		maxCharge = 3360;
		reloadSpeed = 0.9;
		maxHP = 3360;
		HP = maxHP;
		scale = 2;
		width = 128;
		height = 128;
		// range = 7
		init(p[0], p[1]);
	}
	public void update(){
		if (collide()) {
			charged = true;
		}
		if (charged == true) {
			img = getImage("beabeecharged.png");
		} else {
			img = getImage("beabeenotcharged.png");
		}
		move();
	}

	public void shootSuper(ArrayList<Bullet> bullets) {
		for (int i = 0; i < 9; i++){
			bullets.add(new Bullet(team,x+64,y+60,7,theta,140,2,0));
		}
		superCharge =0;
	}

	public void shoot(ArrayList<Bullet> bullets) {
		if (charged == true) {
			bullets.add(new Bullet(team, x + 64, y + 60, 8, theta, 3080, 2, 0));

		} else {
			bullets.add(new Bullet(team, x + 64, y + 60, 8, theta, 1120, 2, 0));
		}
		ammo--;
		reload = reloadSpeed;
		inCombat = true;
		if (collide()) {
			superCharge++;
		}
		if (superCharge == 3) {
			beaSuper = true;
		}
		charged= false;
	}

	// MOVEMENT
	public void move() {
		tx.translate(vy * Math.cos(Math.PI / 2 + theta) / scale,
				vy * Math.sin(Math.PI / 2 + theta) / scale);
		tx.translate(vx * Math.cos(theta) / scale, vx * Math.sin(theta) / scale);
		x += vx;
		y += vy;
	}

	public void spin(double a) {
		// By @ArkyLi
		double oldangle = theta;
		theta = a;
		tx.rotate(oldangle - theta, width / 2 / scale, height / 2 / scale);
	}

	// DRAWING
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
