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
	//size of jframe
	int screen_width 	= 336*4;
	int screen_height 	= 528/2*4;
	int[][] Map = new int[21][25];
	
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		//food
		g.setColor(new Color(100, 231, 100));
		g.fillRect(0,256,2000,1600);
        g.fillRect(448, 0, 448, 256);
		g.fillRect(448, 1600, 448, 256);
		g.setColor(new Color(210, 180, 140));
        g.fillRect(0, 0, 448, 256);
        g.fillRect(896, 0, 448, 256);
        g.fillRect(0, 1600, 448, 256);
        g.fillRect(896, 1600, 448, 256);
	}
	
	public void update() {
		//Player Movement
		repaint();

	}

	public void updateBot(int id){
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
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
		//boost stuff
	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//allow to boost again once key is released
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

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
		
		//System.out.println((mouse[0]-p[0])+","+(mouse[1]-p[1]));
	}

}

