import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	// size of jframe
	int screen_width = 336 * 4;
	int screen_height = 528 / 2 * 4;
	int[][] Map = new int[25][21];
	Grass[] bush = new Grass[80];
	Crate[] crates = new Crate[80];
	//[0] and [1] are safe1s, [2] [3] and [4] are allies, [5] [6] [7] are enemies
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	Shelly shelly0 = new Shelly(0, new int[] { 200, 200 });
	Shelly shelly1 = new Shelly(1, new int[] { 200, 600 });
	Bea bea0 = new Bea(0, new int[] { 400, 200 });
	Bea bea1 = new Bea(1, new int[] { 400, 600 });
	Colt colt0 = new Colt(0, new int[] { 600, 200 });
	Colt colt1 = new Colt(1, new int[] { 600, 600 });
	Safe safe0 = new Safe(0, new int[] { 800, 200 });
	Safe safe1 = new Safe(1,new int[] {800,600});
	
	int player = 0;
	
	boolean keys[] = new boolean[256];
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		//background
		g.setColor(new Color(100, 231, 100));
		g.fillRect(0, 0, 2000, 1600);

		//draw brawlers
		safe0.paint(g);
		safe1.paint(g);
		shelly0.paint(g);
		shelly1.paint(g);
		colt0.paint(g);
		colt1.paint(g);
		bea0.paint(g);
		bea1.paint(g);
		
		for (int i = 0; i < bullets.size(); i++) {
			// System.out.println("bullet " +i);
			Bullet b = bullets.get(i);
			if (b.team == 0) {
				g.setColor(new Color(0, 0, 255));
			} else {

			}
			g.fillOval(b.getX(), b.getY(), 10, 10);
		}

		for (int i = 0; i < bush.length; i++) {
			if (bush[i] != null) {
				bush[i].paint(g);
			}
		}
		// b.paint(g);
		for (int i = 0; i < crates.length; i++) {
			if (crates[i] != null) {
				crates[i].paint(g);
			}
		}
		
		if (safe1.getHP() <= 0) {
			g.setColor(new Color(100, 231, 100));
			g.fillRect(0, 0, screen_width, screen_height);
			g.setColor(new Color(0, 0, 0));
			Font myFont = new Font("Serif", Font.BOLD, 100);
			g.setFont(myFont);
			g.drawString("YOUwU WIN!!", 325, 400);
			g.drawString("GG UwU beat bots", 275, 600);
			Font notMyFont = new Font("Serif", Font.BOLD, 50);
			g.setFont(notMyFont);
			g.drawString("*insert happy noises here*", 380, 800);
			g.drawString(".-.", 650, 875);
		}

		if (safe0.getHP() <= 0) {
			g.setColor(new Color(100, 231, 100));
			g.fillRect(0, 0, screen_width, screen_height);
			g.setColor(new Color(0, 0, 0));
			Font myFont = new Font("Serif", Font.BOLD, 80);
			g.setFont(myFont);
			g.drawString("YOU ARE COMPLETE TRASH", 100, 400);
			g.drawString("GG MEANS GIT GUD", 250, 600);
			Font notMyFont = new Font("Serif", Font.BOLD, 50);
			g.setFont(notMyFont);
			g.drawString("U LOST TO SCUFFED BOTS", 325, 800);
			g.drawString("BRUH U TRASH", 450, 875);
		}
		

	}

	public void update() {
		// bullet Movement
				for (int i = 0; i < bullets.size(); i++) {
					Bullet b = bullets.get(i);
					b.move();
					Brawler[] tars = new Brawler[6];
					tars[0] = shelly0;
					tars[1] = shelly1;
					tars[2] = colt0;
					tars[3] = colt1;
					tars[4] = bea0;
					tars[5] = bea1;
					//brawler collision
					for (int j = 0; j < tars.length; j++) {
						Brawler tar = tars[j];
						if (b.team == tar.team) {
							System.out.println("NO");
							continue;
						}
						if (b.collided(tar.getX() + 59, tar.getY() + 62, 46)) {
							tar.takeDamage(b.getDamage(), b.getEffect());
							b.onHit(tar);
							bullets.remove(i);
							i--;
							break;
						}
					}
					//safe
					if (b.team == 1) {
						// safe hitbox is basically perfect at 64 64 64
						if (b.collided(safe0.getX() + 64, safe0.getY() + 64, 64)) {
							safe0.takeDamage(b.getDamage(), b.getEffect());
							b.onHit(safe0);
							bullets.remove(i);
							i--;
							continue;
						}
					}
					if (b.team == 0) {
						// safe hitbox is basically perfect at 64 64 64
						if (b.collided(safe1.getX() + 64, safe1.getY() + 64, 64)) {
							safe1.takeDamage(b.getDamage(), b.getEffect());
							b.onHit(safe1);
							bullets.remove(i);
							i--;
							continue;
						}
					}
					// crates
					for(int k = 0; k < crates.length; k++) {
						if (crates[k]==null) continue;
						if (b.collided(crates[k].getX()+16, crates[k].getY()+16, 32)) {
							bullets.remove(i);
							i--;
							break;
						}
					}
				}
				// safe1.update(screen_height,bullets);
			

				if (keys[68]) {
					bea0.controlMove(2, -1);
				} else if (keys[65]) {
					bea0.controlMove(1, -1);
				} else {
					bea0.controlMove(0, -1);
				}

				if (keys[87]) {
					bea0.controlMove(-1, 1);
				} else if (keys[83]) {
					bea0.controlMove(-1, 2);
				} else {
					bea0.controlMove(-1, 0);
				}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// bea.move();
		bea0.update(6, bullets);
		//colt.shotPattern(bullets);
		
		update();
		repaint();
	}

	public static void main(String[] args) {
		Driver d = new Driver();

	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Brawlstars but scuffed");
		f.setSize(screen_width, screen_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseListener(this);
		// constructor
		// initialize structures

		f.add(this);

		Timer t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);

		
		
		// images
		// Map = 0 means no image
		// Map = 1 means grass
		// Map = 2 means crates
		for (int i = 0; i < Map.length; i++) {
			for (int k = 0; k < Map[0].length; k++) {
				Map[i][k] = 0;
			}
		}

		// upper left hand square of grass
		Map[1][1] = 1;
		Map[2][1] = 1;
		Map[1][2] = 1;
		Map[2][2] = 1;

		// upper right hand square of grass
		Map[1][18] = 1;
		Map[1][19] = 1;
		Map[2][18] = 1;
		Map[2][19] = 1;

		// upper middle grass
		Map[1][8] = 1;
		Map[1][9] = 1;
		Map[1][10] = 1;
		Map[1][11] = 1;
		Map[1][12] = 1;

		// middle left hand side of grass
		Map[9][1] = 1;
		Map[10][1] = 1;
		Map[11][1] = 1;
		Map[11][2] = 1;
		Map[11][3] = 1;

		Map[13][3] = 1;
		Map[13][2] = 1;
		Map[13][1] = 1;
		Map[14][1] = 1;
		Map[15][1] = 1;

		// center grass
		Map[8][8] = 1;
		Map[8][9] = 1;
		Map[8][10] = 1;
		Map[8][11] = 1;
		Map[8][12] = 1;
		Map[9][8] = 1;
		Map[9][10] = 1;
		Map[9][12] = 1;

		Map[15][8] = 1;
		Map[15][10] = 1;
		Map[15][12] = 1;
		Map[16][8] = 1;
		Map[16][9] = 1;
		Map[16][10] = 1;
		Map[16][11] = 1;
		Map[16][12] = 1;

		// middle right hand side grass
		Map[9][19] = 1;
		Map[10][19] = 1;
		Map[11][19] = 1;
		Map[11][18] = 1;
		Map[11][17] = 1;

		Map[13][17] = 1;
		Map[13][18] = 1;
		Map[13][19] = 1;
		Map[14][19] = 1;
		Map[15][19] = 1;

		// bottom left hand square of grass
		Map[22][1] = 1;
		Map[22][2] = 1;
		Map[23][1] = 1;
		Map[23][2] = 1;

		// bottom right hand square of grass
		Map[22][18] = 1;
		Map[22][19] = 1;
		Map[23][18] = 1;
		Map[23][19] = 1;

		// bottom middle grass
		Map[23][8] = 1;
		Map[23][9] = 1;
		Map[23][10] = 1;
		Map[23][11] = 1;
		Map[23][12] = 1;

		// upper center boxes
		Map[2][6] = 2;
		Map[2][7] = 2;
		Map[2][8] = 2;
		Map[2][9] = 2;
		Map[2][10] = 2;
		Map[2][11] = 2;
		Map[2][12] = 2;
		Map[2][13] = 2;
		Map[2][14] = 2;

		// left hand side crates
		Map[6][4] = 2;
		Map[6][5] = 2;
		Map[7][5] = 2;

		Map[8][1] = 2;
		Map[8][2] = 2;
		Map[9][2] = 2;
		Map[10][2] = 2;
		Map[10][3] = 2;

		Map[14][3] = 2;
		Map[14][2] = 2;
		Map[15][2] = 2;
		Map[16][2] = 2;
		Map[16][1] = 2;

		Map[17][5] = 2;
		Map[18][5] = 2;
		Map[18][4] = 2;

		// center crates
		Map[9][9] = 2;
		Map[9][11] = 2;
		Map[10][8] = 2;
		Map[10][9] = 2;
		Map[10][10] = 2;
		Map[10][11] = 2;
		Map[10][12] = 2;

		Map[14][8] = 2;
		Map[14][9] = 2;
		Map[14][10] = 2;
		Map[14][11] = 2;
		Map[14][12] = 2;
		Map[15][9] = 2;
		Map[15][11] = 2;

		// ride hand side crates
		Map[6][15] = 2;
		Map[6][16] = 2;
		Map[7][15] = 2;

		Map[8][19] = 2;
		Map[8][18] = 2;
		Map[9][18] = 2;
		Map[10][18] = 2;
		Map[10][17] = 2;

		Map[14][17] = 2;
		Map[14][18] = 2;
		Map[15][18] = 2;
		Map[16][18] = 2;
		Map[16][19] = 2;

		Map[17][15] = 2;
		Map[18][15] = 2;
		Map[18][16] = 2;

		// bottom middle crates
		Map[21][8] = 2;
		Map[21][10] = 2;
		Map[22][6] = 2;
		Map[22][7] = 2;
		Map[22][8] = 2;
		Map[22][9] = 2;
		Map[22][10] = 2;
		Map[22][11] = 2;
		Map[22][12] = 2;
		Map[22][13] = 2;
		Map[22][14] = 2;
		int z = 0;
		int c = 0;
		for (int i = 0; i < 25; i++) {
			for (int k = 0; k < 21; k++) {
				if (Map[i][k] == 1) {
					bush[z] = new Grass(k * 64, i * 64, "bush.png");
					z++;
				}
			}
		}
		for (int i = 0; i < 25; i++) {
			for (int k = 0; k < 21; k++) {
				if (Map[i][k] == 2) {
					crates[c] = new Crate(k * 64, i * 64, "crate.png");
					c++;
				}
			}
		}
		// Rgoal[0] = new Redgoal(448,256-64,"redgoal.png");
		// Rgoal[1] = new Redgoal(448,256-64+2000,"redgoal.png");

	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		/*
		 * //bea if (e.getKeyCode()==65) {bea.controlMove(65, 0);} if
		 * (e.getKeyCode()==68) {bea.controlMove(68, 0);} if (e.getKeyCode()==87)
		 * {bea.controlMove(0, 87);} if (e.getKeyCode()==83) {bea.controlMove(0, 83);}
		 * //colt if (e.getKeyCode()==65) {colt.controlMove(65, 0);} if
		 * (e.getKeyCode()==68) {colt.controlMove(68, 0);} if (e.getKeyCode()==87)
		 * {colt.controlMove(0, 87);} if (e.getKeyCode()==83) {colt.controlMove(0, 83);}
		 */
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		 * // bea if (e.getKeyCode()==65) {bea.controlMove(-1, 0);} if
		 * (e.getKeyCode()==68) {bea.controlMove(-1, 0);} if (e.getKeyCode()==87)
		 * {bea.controlMove(0, -1);} if (e.getKeyCode()==83) {bea.controlMove(0, -1);}
		 * //colt if (e.getKeyCode()==65) {colt.controlMove(-1, 0);} if
		 * (e.getKeyCode()==68) {colt.controlMove(-1, 0);} if (e.getKeyCode()==87)
		 * {colt.controlMove(0, -1);} if (e.getKeyCode()==83) {colt.controlMove(0, -1);}
		 */
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// bea.shoot(bullets);
		if (e.getButton() == MouseEvent.BUTTON1) {
			System.out.println("CLICK");
		bea0.shoot(bullets);
		}

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
		// bea.spin(bea.getAngle(m.getX()-2,m.getY()-20));
		bea0.spin(bea0.getAngle(m.getX() - 2, m.getY() - 20));
		// System.out.println((mouse[0]-p[0])+","+(mouse[1]-p[1]));
	}
	
	

}
