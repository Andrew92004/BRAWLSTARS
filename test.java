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
	int screen_width 	= 900;
	int screen_height 	= 800;
	double scale = 1.0;

	int[] mouse = new int[2];
	int [] cam = {0,0};
	int [] area = {3600,3200};
	
	int max = 400;
	int[][] food = new int[max][2];//x, y
	int foodsize = 7;
	
	int[] rads = new int[max];
	int[][] players = new int[max/25][4];//x, y, boost, run away counter
	
	int prad =30;
	int[] p = {0,0};//x,y
	int boost = 0;
	boolean boostready = true;
	
	int minboostmass = 50;
	//players[0]=p;
	
	Color[] colors = new Color[max];
	
	// reading a val from a 1d array
	// System.out.print( x[0]); //reading value
	// x[0] = 5; //writing is similar to regular variables but now you have to specify WHERE
	public void paint(Graphics g) {
		super.paintComponent(g);
		//food
		g.setColor(new Color(219,219,219));
		for (int i = -area[0]/2; i < area[0]/2; i+=30/scale){
			g.drawLine(i-(int)((p[0]/scale)%30),0,i-(int)((p[0]/scale)%30),screen_height);
		}
		for (int i = -area[0]/2; i < area[0]/2; i+=30/scale){
			g.drawLine(0,i-(int)((p[1]/scale)%30),screen_width,i-(int)((p[1]/scale)%30));
		}
		for (int i = 0; i < max; i++){
			g.setColor(colors[i]);
			g.fillOval((int) (screen_width/2+(food[i][0]-foodsize-cam[0]-screen_width/2)/scale), (int) (screen_height/2+(food[i][1]-foodsize-cam[1]-screen_height/2)/scale),
					(int) (foodsize*2/scale), (int) (foodsize*2/scale));
		}
		//bots
		for (int i = 0; i < max/25; i++){
			g.setColor(new Color(255,0,0));
			g.fillOval((int) (screen_width/2+(players[i][0]-rads[i]-cam[0]-screen_width/2)/scale), (int) (screen_height/2+(players[i][1]-rads[i]-cam[1]-screen_height/2)/scale),
					(int) (rads[i]*2/scale), (int) (rads[i]*2/scale));
		}
		//the player
		g.fillOval((int) (screen_width/2-(prad/scale)), (int) (screen_height/2-(prad/scale)), (int) (prad*2/scale), (int) (prad*2/scale));
		//testing drawings
		//g.clearRect(screen_width/2-10, screen_height/2-10, 20, 20);
		//g.clearRect(screen_width/2+3*p[2],screen_height/2+3*p[3],10,10);
		//g.clearRect(p[0]-cam[0]-5,p[1]-cam[1]-5,10,10);
		//g.clearRect(mouse[0],mouse[1],10,10);
	}//end of paint method - put code above for anything dealing with drawing -
	
	
	
	Font font = new Font ("Courier New", 1, 50);
	public void update() {
		//Player Movement
		double ang = Math.atan((mouse[1]-screen_height/2)/((mouse[0]-screen_width/2)==0 ? 0.001 : (mouse[0]-screen_width/2)));
		if (mouse[0]-screen_width/2<=0){
			ang+=Math.PI;
		}
		int maxspeed = 500/prad+1;
		double dist = distance(mouse[0],mouse[1],screen_width/2,screen_height/2);
		double speed = dist<prad ? 0: dist > 100 ? maxspeed: maxspeed*(dist-prad)/(100);//(limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin
		//System.out.println(mouse[1]-screen_height/2);
		//boost
		if (boost>0){
			if (boost==8) prad*= 0.7;
			speed=boost*7;
			boost--;
		}
		//position update
		if (p[0] + Math.cos(ang)*speed>=area[0]/2 || p[0] + Math.cos(ang)*speed <= -area[0]/2) p[0]-=(int) (Math.cos(ang)*speed);
		if (p[1] + Math.sin(ang)*speed>=area[1]/2 || p[1] + Math.sin(ang)*speed <= -area[1]/2) p[1]-=(int) (Math.sin(ang)*speed);
		p[0]+=(int) (Math.cos(ang)*speed);
		p[1]+=(int) (Math.sin(ang)*speed);
		//camera update
		cam[0]=p[0]-screen_width/2;
		cam[1]=p[1]-screen_height/2;
		scale = 0.75+prad/400.0;
		
		//update bots
		for (int i = 0; i < players.length; i++){
			updateBot(i);
		}

		//food collision
		for (int i = 0; i < max; i++){
			if (distance(p[0],p[1],food[i][0],food[i][1])<=prad+foodsize){
				food[i][0]=(int)(Math.random()*area[0]-area[0]/2);
				food[i][1]=(int)(Math.random()*area[1]-area[1]/2);
				colors[i]=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
				prad++;
			}
			for (int j = 0; j < players.length; j++){
				if (distance(players[j][0],players[j][1],food[i][0],food[i][1])<=rads[j]+foodsize){
					food[i][0]=(int)(Math.random()*area[0]-area[0]/2);
					food[i][1]=(int)(Math.random()*area[1]-area[1]/2);
					colors[i]=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
					rads[j]++;
				}
			}
		}
		//player collisions (for each bot)
		for (int i = 0; i < players.length; i++){
			//with user
			if (prad>rads[i]){
				if (distance(p[0],p[1],players[i][0],players[i][1])<=prad){
					prad+=rads[i]/2;
					players[i][0]=(int)(Math.random()*area[0]-area[0]/2);
					players[i][1]=(int)(Math.random()*area[1]-area[1]/2);
					rads[i]=30;
				}
			}
			if (prad<rads[i]){
				if (distance(p[0],p[1],players[i][0],players[i][1])<=rads[i]){
					rads[i]+=prad/2;
					p[0]=(int)(Math.random()*area[0]-area[0]/2);
					p[1]=(int)(Math.random()*area[1]-area[1]/2);
					prad = 30;
				}
			}
			//with other bots
			for (int j = 0; j < players.length; j++){
				if (rads[j]>rads[i]){
					if (distance(players[j][0],players[j][1],players[i][0],players[i][1])<=rads[j]){
						rads[j]+=rads[i]/2;
						players[i][0]=(int)(Math.random()*area[0]-area[0]/2);
						players[i][1]=(int)(Math.random()*area[1]-area[1]/2);
						rads[i]=30;
					}
				}
				if (rads[j]<rads[i]){
					if (distance(players[j][0],players[j][1],players[i][0],players[i][1])<=rads[i]){
						rads[i]+=rads[j]/2;
						players[j][0]=(int)(Math.random()*area[0]-area[0]/2);
						players[j][1]=(int)(Math.random()*area[1]-area[1]/2);
						rads[j]=30;
					}
				}
			}
		}
		
		
		//krappy game ending thing
		if (prad>500){
			System.out.println("YOU WINNNNN");
			System.exit(0);
		}
	}//end of update method - put code above for any updates on variable
	//find distance between 2 points
	public double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2,2));
	}
	//find the nearest coords out of an array
	public int findNearest(int x, int y, int[][] pos, int omit){
		int index = 0;
		double small = distance(x,y,pos[0][0],pos[1][1]);
		for (int i = 0; i < pos.length; i++){
			if (distance(x,y,pos[i][0],pos[i][1])<small&&i!=omit){
				index = i;
				small = distance(x,y,pos[i][0],pos[i][1]);
			}
		}
		return index;
	}
	
	public void updateBot(int id){
		int x = players[id][0];
		int y = players[id][1];
		int rad = rads[id];
		int detection = 800;
		//find closest enemy
		int nearEnemy = findNearest(x,y,players,id);
		int distPlay = (int)distance(x,y,p[0],p[1]);
		int distBot = (int)distance(x,y,players[nearEnemy][0],players[nearEnemy][1]);
		if (distPlay<distBot) nearEnemy = -1;
		//is it aggro
		boolean aggro;
		if (nearEnemy!=-1) aggro = rad > rads[nearEnemy]  && distBot<detection;
		else aggro = rad > prad && distPlay<detection;
		//coords for target
		int[] tar = new int[2];
		int trad = nearEnemy==-1 ? prad : rads[nearEnemy];
		
		if (aggro){//aggro tries to find player
			if (nearEnemy==-1){
				tar[0]=p[0];
				tar[1]=p[1];
			}else{
				tar[0]=players[nearEnemy][0];
				tar[1]=players[nearEnemy][1];
			}
			//boost
			if (rad>minboostmass && rad*0.8 > trad && trad>rad*0.4&& distance(x,y,tar[0],tar[1])<300 && players[id][2]==0){
				players[id][2]=8;
				rads[id]*= 0.7;
			}
		}else{//passive tries to find food
			int nearid = findNearest(x,y,food,-1);
			tar[0]=food[nearid][0];
			tar[1]=food[nearid][1];
			//run away
			if (nearEnemy==-1){
				if (prad>rad&&distance(p[0],p[1],x,y)<detection/2){
					players[id][3]=25;
				}
			}else{
				if (rads[nearEnemy]>rad&&distance(players[nearEnemy][0],players[nearEnemy][1],x,y)<detection/2){
					players[id][3]=25;
				}
			}
		}
		
		if (players[id][3]>0){
			if (nearEnemy==-1){
				tar[0]=x+(x-p[0]);
				tar[1]=y+(y-p[1]);
			}
			else{
				tar[0]=x+(x-players[nearEnemy][0]);
				tar[1]=y+(y-players[nearEnemy][1]);
			}
			players[id][3]--;
		}
		
		double ang = Math.atan((tar[1]-y)/((tar[0]-x)==0 ? 0.001 : (tar[0]-x)));
		if (tar[0]-x<=0){
			ang+=Math.PI;
		}
		int speed = 500/rad;
		//boost
		if (players[id][2]>0){
			speed = players[id][2]*7;
			players[id][2]--;
		}
		//border detection
		if (x>=area[0]/2 || x <= -area[0]/2) players[id][0]-=(int) (Math.cos(ang)*speed);
		if (y>=area[1]/2 || y <= -area[1]/2) players[id][1]-=(int) (Math.sin(ang)*speed);
		//update
		players[id][0]+=(int) (Math.cos(ang)*speed);
		players[id][1]+=(int) (Math.sin(ang)*speed);
	}

	//==================code above ===========================
	
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
		for (int i = 0; i < max; i++){
			food[i][0]=(int)(Math.random()*area[0]-area[0]/2);
			food[i][1]=(int)(Math.random()*area[1]-area[1]/2);
			colors[i]=new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
		}

		for (int i = 0; i < max/25; i++){
			players[i][0]=(int)(Math.random()*area[0]-area[0]/2);
			players[i][1]=(int)(Math.random()*area[1]-area[1]/2);
			players[i][2]=0;
			rads[i]=30;
		}
		
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
		if (e.getKeyCode()==32&&boostready&&prad>minboostmass){
			boost = 8;
			boostready = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//allow to boost again once key is released
		boostready = true;
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
		mouse[0]=m.getX()-1;
		mouse[1]=m.getY()-26;
		//System.out.println((mouse[0]-p[0])+","+(mouse[1]-p[1]));
	}
	
}
