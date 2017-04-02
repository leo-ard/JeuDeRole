package game.core;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.jhlabs.image.PerspectiveFilter;

import game.armes.Gun;
import game.audio.musicPlayer;
import game.champions.Champions;
import game.champions.PersoSprite;
import game.champions.personnages.Lore;
import game.map.*;
import game.mobs.*;
import game.mobs.enemies.Corrompu;
import gui.GUI;

public class GamePane extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1204454795981689683L;
	
	//----FIELDS----// 
	
	//essentials
	protected Thread thread;
	public static int WIDTH = 1280;
	public static int HEIGHT = 800;
	public static View v;
	public static int mousePosX;
	public static int mousePosY;
	
	//frame
	protected final int FPS = 100;
	public double averageFPS = FPS;
	
	//map
	public static Map map;
	
	//image
	protected Graphics2D gGame;
	protected Graphics2D gUI;
	public BufferedImage image;
	
	//imported images
	/**
	 * blocks ids
	 */
	public static Image[] texturesBlock = new Image[10];
	/**
	 * 0 : selected block
	 * 1 : redSelected block
	 * 2 : pauseMenu
	 */
	public static Image[] texturesGUI = new Image[4];
	/**
	 * 
	 */
	public static Image[] personnageTexture = new Image[10];
	public static PersoSprite[] personnageSprite = new PersoSprite[10];
	public static int curentLevel = 2;
	
	//music
	public static musicPlayer mp;
	
	//cursor
	// Transparent 16 x 16 pixel cursor image.
	static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	// Create a new blank cursor.
	public static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	public static final Cursor DEFAULT_CURSOR = Cursor.getDefaultCursor();

	//mobs
	public static Champions player;
	public ArrayList<Corrompu> corrompus;
	
	public static Listener l;
	
	public boolean running;
	
	public static Color filtre;
	
	public static boolean WB = false;
	
	public static BufferedImage lastImage = new BufferedImage(GamePane.WIDTH, GamePane.HEIGHT, BufferedImage.TYPE_INT_ARGB);

	

	/**
	 * Constructor of the GamePane class.
	 * 
	 */
	public GamePane(){
		super();
		setSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		
		thread = new Thread(this);
		
		requestFocus();
	}
	
	/**
	 * restart the thread of this class, restart the game :)
	 * 
	 */
	public void restart(){
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Initialise tout les varriables necessaire au lancement du jeux
	 * 
	 * 
	 */
	public void initiate(){
		//import images
		textureImport();
		filtre = new Color(0,0,0,0);
		//music
		mp = new musicPlayer(new String[]{"assets\\audio\\Concerto.mp3"}, true);
		//mp.play(); TODO play the music
		Gun.loadGuns();
		//all fields for the game will be initiate here
		map = new Map("start");
		//map.Generate(4, 0, 0);
		
		//Graphics
		AbsolutInit();
		
		v = new View();
		
		//TODO change the position by the saved one
		player = new Lore(0,0);
		corrompus = new ArrayList<Corrompu>();
		corrompus.add(new Corrompu(1280,800));
		running = true;
		
	}
	
	/**
	 * ce qui est oubligatoire a Initialiser, tres utile pour les classe qui hérite de celle ci
	 * 
	 */
	public void AbsolutInit(){
		l = new Listener();
		this.addKeyListener(l);
		this.addMouseListener(l);
		this.addMouseWheelListener(l);
	}
	
	/**
	 * Importe les textures
	 */
	public void textureImport(){
		texturesBlock[1] = new ImageIcon("assets/textures/map/Grass.png").getImage();
		texturesBlock[2] = new ImageIcon("assets/textures/map/Eau.png").getImage();
		texturesBlock[3] = new ImageIcon("assets/textures/map/Pierre.png").getImage();
		texturesBlock[4] = new ImageIcon("assets/textures/map/rock.png").getImage();
		
		texturesGUI[0] = new ImageIcon("assets/textures/gui/Selected.png").getImage();
		texturesGUI[1] = new ImageIcon("assets/textures/gui/redSelection.png").getImage();
		texturesGUI[2] = new ImageIcon("assets/textures/gui/pauseMenu.png").getImage();
		texturesGUI[3] = new ImageIcon("assets/textures/gui/barreDeVie.png").getImage();
		
		//personnageSprite[0] = new PersoSprite();
		
		personnageTexture[0] = new ImageIcon("assets/textures/player/tetes.png").getImage();
		personnageTexture[1] = new ImageIcon("assets/textures/player/multi/perso0.png").getImage();
	}
	
	public void addNotify(){
		super.addNotify();
	}
	
	

	/**
	 * lancement du thread de GamePane
	 */
	public void run() {
		
		//---- Innitiation ----//
		
		initiate();
		//---- field for the fps system ----//
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / FPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		//----Thread----//

		requestFocus();
		while(true){
			
	        long currentTime = System.nanoTime();
	        deltaU += (currentTime - initialTime) / timeU;
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;

	        if (deltaU >= 1) {
				//PauseMenu
				Frame.pPause.update();
	        	
				if(running){
					GameUpdate();

				}
	            ticks++;
	            deltaU--;
	        }

	        if (deltaF >= 1) {
	        	GamePane.WIDTH = Frame.frame.getContentPane().getWidth();
				GamePane.HEIGHT = Frame.frame.getContentPane().getHeight();
	        	if(running){
	        		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
					gGame = (Graphics2D) image.getGraphics();
					gUI = (Graphics2D) image.getGraphics();
					gGame.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					gGame.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					gUI.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					gUI.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					GameRenderPalet();
					GameDraw();
	        	}
	            frames++;
	            deltaF--;
	        }
	        if (System.currentTimeMillis() - timer > 1000) {
	        	averageFPS = frames;
	            frames = 0;
	            ticks = 0;
	            timer += 1000;
	        }
		}
		
	}
	
	public void mouseUpdate(){
		try{
			mousePosX = this.getMousePosition().x;
			mousePosY = this.getMousePosition().y;
			mousePosX+=player.getX()-GamePane.WIDTH/2;
			mousePosY+=player.getY()- GamePane.HEIGHT/2;
		}catch(NullPointerException e){}
	}
	
	public void pause(){
		running = false;
	}
	
	public void resume(){
		running = true;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	/**
	 * Tout ce qui est dans cette fonction est update  chaque frame
	 * 
	 */
	public void GameUpdate(){
		player.update();
		map.update();
		
		v.update();
		
		//---- Map Update ----//
		map.setSelectedBlock(mousePosX, mousePosY);
		
		this.mouseUpdate();
		
		HitBox.hitboxes.clear();
		//System.out.println("--------------------------------------");
	}
	
	/**
	 * ce qui est oubligatoire de dessiner, tres utiles pour les classe qui herite de celle ci
	 */
	public void GameRenderPalet(){
		gGame.setColor(Color.gray);
		gGame.fillRect(0, 0, WIDTH, HEIGHT);
		
		gGame.translate(v.x, v.y);
		
		GameRender();
		
		gGame.setColor(Color.black);
		gGame.setFont(new Font("Arial", 0, 20));
		gGame.translate(-gGame.getTransform().getTranslateX(), -gGame.getTransform().getTranslateY());
		gUI.setColor(Color.black);
		gUI.drawString("FPS: "+averageFPS, 0, 10);
		
	}
	
	/**
	 * Dessine le jeux
	 */
	public void GameRender(){
		//----MAP----//
		map.draw(gGame);
		gGame.setColor(new Color(0,0,0));
		gGame.fillRect(GamePane.mousePosX, GamePane.mousePosY, 2, 2);
		map.drawAfter(gGame);
		
		//----MOBS----//
		player.draw(gGame);
		for(int i = 0; i< corrompus.size(); i++){
			corrompus.get(i).draw(gGame);
		}
		
		//----GUI----//
		GUI.draw(gUI);
		
		gUI.setColor(filtre);
		gUI.fillRect(0, 0, GamePane.WIDTH, GamePane.HEIGHT);
	}
	
	/**
	 * applique le dessin du jeu fait plus haut et le met sur lecran
	 */
	public void GameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0,0, null);
		lastImage = image;
		g2.dispose();
		
	}
	
	/**
	 * @return L'angle entre le joueur et la sourie
	 */
	public static double getAngleOfTheMouseAndThePlayer(){
		double AB = mousePosX - GamePane.player.getX();
		double AC = mousePosY - GamePane.player.getY();
		
		return Math.toDegrees(Math.atan2(AC,AB));
	}
	
	/**
	 * angle entre deux point 
	 * 
	 * p1 (x1, y1)
	 * p2 (x2, y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getAngle(double x1, double y1, double x2, double y2){
		double AB =  x1 - x2;
		double AC =  y1 - y2;
		
		return Math.toDegrees(Math.atan2(AC,AB));
	}
	
	/**
	 * chaque block possede un x et un y sur la map, pour convertir ces possition en x, y absolut, il faut utiiser cette fonction
	 * 
	 * @param t
	 */
	public static int getXOfMapByPixel(Point t){
		return map.getBlockByPixel((int)t.getX(), (int)t.getY()).getX();
	}
	
	public static int getYOfMapByPixel(Point t){
		return map.getBlockByPixel((int)t.getX(), (int)t.getY()).getY();
	}
	
	//---- getter and setter ----//

	public double getAverageFPS() {
		return averageFPS;
	}

	public void setAverageFPS(double averageFPS) {
		this.averageFPS = averageFPS;
	}

}
