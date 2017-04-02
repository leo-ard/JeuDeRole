package game.map;

import java.awt.Point;

import game.core.GamePane;

public class View {
	
	public int zoom;
	public int height;
	private final int baseBlockPixelHeight = 100;
	private final int baseBlockPixelWidth = 100;  
	
	public int blockPixelHeight = 100;
	public int blockPixelWidth = 100;
	
	public int x = 0;
	public int y = 0;
	
	
	public View() {
		zoom = 10;
	}
	
	public void update(){
		/*blockPixelHeight = (int) (double)(baseBlockPixelHeight*zoom/10);
		blockPixelWidth = (int) (double)(baseBlockPixelWidth*zoom/10);*/
		
		x = -GamePane.player.getX()+GamePane.WIDTH/2;
		y = -GamePane.player.getY()+GamePane.HEIGHT/2;
	}
	
	public void editorUpdate(){
		blockPixelHeight = (int) (double)(baseBlockPixelHeight*zoom/10);
		blockPixelWidth = (int) (double)(baseBlockPixelWidth*zoom/10);
		
		x = -GamePane.player.getX()+GamePane.WIDTH/2-200;
		y = -GamePane.player.getY()+GamePane.HEIGHT/2;
	}

	public double getZoom() {
		return zoom;
	}

	public void zoom(int nb) {
		/*int xx = 0;
		int yy = 0;
		
		this.zoom -= nb;
		x+=xx*nb;
		y+=yy*nb;
		
		if(zoom > 20){
			zoom = 20;
			x+=xx;
			y+=yy;
		}
		if(zoom < 5){
			zoom = 5;
			x-=xx;
			y-=yy;
		}*/
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public  double putInRationX(int nb){
		return (double)nb/(double)this.blockPixelWidth;
	}
	
	public double putInRationY(int nb){
		return (double)nb/(double)this.blockPixelHeight;
	}

	
	public int getFromRationX(double nb){
		return (int) ((double)nb*(double)this.blockPixelWidth);
	}
	
	public int getFromRationY(double nb){
		return (int) ((double)nb*(double)this.blockPixelHeight);
	}
	
	public void setHeight(int h){
		this.blockPixelHeight = h;
		this.blockPixelWidth = h;
	}
	
	public Point getPointByPoint(Point p, View vi){
		
		double rationX = vi.putInRationX((int)p.getX());
		double rationY = vi.putInRationY((int)p.getY());
		Point pt = new Point(this.getFromRationX(rationX), this.getFromRationY(rationY));
		return pt;
	
	}
	
	/*public static void zoomIn(){
		double rationX = View.putInRationX(GamePane.player.getX());
		double rationY = View.putInRationY(GamePane.player.getY());
		GamePane.v.zoom++;
		GamePane.v.update();
		GamePane.player.setX(View.getFromRationX(rationX));
		GamePane.player.setY(View.getFromRationX(rationY));
	}
	
	public static void zoomOut(){
		double rationX = View.putInRationX(GamePane.player.getX());
		double rationY = View.putInRationY(GamePane.player.getY());
		GamePane.v.zoom--;
		GamePane.v.update();
		GamePane.player.setX(View.getFromRationX(rationX));
		GamePane.player.setY(View.getFromRationX(rationY));
	}*/
}

//TODO move Cursor from GamePane to View