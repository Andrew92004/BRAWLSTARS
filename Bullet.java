import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Bullet {
	private int x, y;
	private int vel;
	private double angle;
	private Image img;
	
	public Bullet(String texture, int[] p, int v, double angle){
		
		img = getImage("bea.png");
		x = p[0];
		y = p[1];
		vel = v;
		//range = 7
		init(p[0],p[1]);
	}

	
	//DRAWING
		private AffineTransform tx = AffineTransform.getTranslateInstance(x,y);

		// draw the affinetransform
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, tx, null);
			}

			private void init(double a, double b) {
				tx.setToTranslation(a, b);
				//tx.scale(scale, scale);
			}

			// converts image to make it drawable in paint
			private Image getImage(String path) {
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
