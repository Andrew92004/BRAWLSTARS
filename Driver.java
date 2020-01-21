import java.applet.Applet;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	//size of jframe
	int screen_width 	= 336*4;
	int screen_height 	= 528/2*4;
	int[][] Map = new int[21][25];//21x25
	
	
	
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
		
		//images
		// Map = 0 means no image
		// Map = 1 means grass
		// Map = 2 means crates
		for(int i = 0; i < Map.length; i++){
			for(int k = 0; k < Map[0].length; k++){
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

