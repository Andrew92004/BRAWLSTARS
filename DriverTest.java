import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	/*Instructions
	 * mouse to move
	 * eat food and bots to get phat
	 * space to boost
	 * COMMENTS:
	 * 
	 * amazing game, the grid looks good.
	 * good game
	 * really cool game, looks similar to the actual game, but maybe make player and enemies color different, a little confusing
	 * make the enemies diff color gj
	 * the game is really accurate and  like how when you split you dash forwards.
	 * 
	 * 
	 */
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	//size of jframe
	int screen_width 	= 336*4;
	int screen_height 	= 528/3*4;
	int[][] Map = new int[21][33];
	
	int[] pp = {200,200};
	Shelly bea = new Shelly(0,pp);
	
	int[] mou = {0,0};
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//food
		g.setColor(new Color(100, 231, 100));
		g.fillRect(0,256,2000,3000);

		bea.paint(g);
		//g.drawOval(bea.getX(), bea.getY(), 10, 10);
		g.drawOval(mou[0],mou[1],10,10);
		
		for (int i = 0; i < bullets.size(); i++){
			//System.out.println("bullet " +i);
			Bullet b = bullets.get(i);
			b.move();
			if (b.team == 0){
				g.setColor(new Color(0,0,255));
			}else{
				
			}
			g.fillOval(b.getX(),b.getY(),10,10);
		}
	}
	
	public void update() {
		//Player Movement
		
	}
	//==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		bea.move();
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	public Driver(){
		JFrame f = new JFrame();
		f.setTitle("Agar.io");
		f.setSize(screen_width, screen_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);

		//constructor
		//initialize structures
		

		f.add(this);


		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==37) {bea.controlMove(37, 0);}
		if (e.getKeyCode()==39) {bea.controlMove(39, 0);}
		if (e.getKeyCode()==38) {bea.controlMove(0, 38);}
		if (e.getKeyCode()==40) {bea.controlMove(0, 40);}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==37) {bea.controlMove(-1, 0);}
		if (e.getKeyCode()==39) {bea.controlMove(-1, 0);}
		if (e.getKeyCode()==38) {bea.controlMove(0, -1);}
		if (e.getKeyCode()==40) {bea.controlMove(0, -1);}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("CLICK");
		bea.shoot(bullets);
	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent m) {
		// TODO Auto-generated method stub
		bea.spin(bea.getAngle(m.getX()-2,m.getY()-20));
		mou[0] = m.getX()-2;
		mou[1] = m.getY()-20;
	}

}
