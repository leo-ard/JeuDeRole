package game.mobs;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import game.abilities.Ability;
import game.armes.Slash;
import game.champions.Champions;
import game.core.GamePane;
import game.map.Block;

public class HitBox {
	public static final int RECTANGLE = 0;
	public static final int CERCLE = 1;
	public static final int ARC = 2;
	public static final int LINE = 3;
	
	public static final int ENEMYDAMAGE = 10;
	public static final int ENEMYCHAMPION = 11;
	public static final int ALLYDAMAGE = 20;
	public static final int ALLYCHAMPION = 21;
	
	public Entity e;
	public int type;
	public int source;
	
	public boolean done;
	 
	public static HashMap<Point, ArrayList<HitBox>> hitboxes = new HashMap<Point, ArrayList<HitBox>>();
	
	public HitBox(int type, Entity e, int use){
		this.type = type;
		this.e = e;
		this.source = use;
		
		done = false;
	}
	
	public void update(){
		if(type == 0){
			// b1    b2
			//
			// b3    b4
			int x = e.getX()-e.WIDTH/2;
			int y = e.getY()-e.getHeight()/2;
			
			Point b1 = GamePane.map.getBlockPointByPixel(x,y);
			Point b2 = GamePane.map.getBlockPointByPixel(x+e.getWidth(),y);
			Point b3 = GamePane.map.getBlockPointByPixel(x,y+e.getHeight());
			Point b4 = GamePane.map.getBlockPointByPixel(x+e.getWidth(),y+e.getHeight());
	
			if(b1.equals(b2)&&b1.equals(b3)&&b1.equals(b4)){
				HitBox.add(b1,this);
				//System.out.println("1:"+b1);
			}
			else if(b1.equals(b2)&&b3.equals(b4)){
				HitBox.add(b1,this);
				HitBox.add(b3,this);
				//System.out.println("2:"+b1+" "+b3);
			}
			else if(b1.equals(b3)&&b2.equals(b4)){
				HitBox.add(b1,this);
				HitBox.add(b2,this);
				//System.out.println("3:"+b1+" "+b2);
			}
			else{
				HitBox.add(b1,this);
				HitBox.add(b2,this);
				HitBox.add(b3,this);
				HitBox.add(b4,this);
				//System.out.println("4:"+b1+" "+b2+" "+b3+" "+b4);
			}
				
			
		}
		//System.out.println(hitboxes.size());
	}
	
	public static void add(Point p , HitBox h){
		//System.out.println(p.getX()+" "+p.getY());
		if(!hitboxes.containsKey(p)){
			ArrayList<HitBox> hb = new ArrayList<HitBox>();
			hb.add(h);
			hitboxes.put(p,hb);
		}
		else if(hitboxes.containsKey(p)){
			hitboxes.get(p).add(h);
		}
		
	}
	
	public static void updateHits(){
		Iterator<Point> points = hitboxes.keySet().iterator();
		ArrayList<HitBox> currentHit = new ArrayList<HitBox>();
		Point currentPoint;
		
		//boucle dans tout les points ayant une hitbox
		while(points.hasNext()){
			currentPoint = points.next();
			currentHit = hitboxes.get(currentPoint);
			if(currentHit.size()>=2){
				int n = currentHit.size();
				//boucle speciale qui bouche a traver tout les elements (i, j) mais sans jamais que deux i,j ou j,i se rencontre
				for(int i = 0; i < n-1;i++){
					for(int j = n-1; j > i; j--){
						//verifis sil y a une collision
						if(currentHit.get(i).collision(currentHit.get(j))){
							currentHit.get(i).hit(currentHit.get(j));
							currentHit.get(j).hit(currentHit.get(i));
							
						}
					}
				}
			}
			
			//System.out.println(currentPoint.getX()+" "+currentPoint.getY()+" "+ currentHit.size());
		}
		
		
	}
	
	public boolean collision(HitBox hb){
		if(this.type == 0){
			if(hb.type == 0){
				if (this.e.getX() < hb.e.getX() + hb.e.getWidth() &&
				this.e.getX() + this.e.getWidth() > hb.e.getX() &&
				this.e.getY() < hb.e.getY() + hb.e.getHeight() &&
				this.e.getHeight() + this.e.getY() > hb.e.getY()){
					return true;
				}
			}
			else if(hb.type == 1){
				
			}
			
			
			
		}
		
		return false;
		
	}
	
	public void draw(Graphics2D g){
		
		
	}
	
	public void hit(HitBox box){
		//System.out.println(this.source+" "+(this.source+"").charAt(0)+" "+box.source+" "+(box.source +"").charAt(0));
		if((this.source+"").charAt(0) == (box.source +"").charAt(0)){
			//System.out.println("TRUE");
		}
		//enemy source
		if((this.source+"").charAt(1) == '1'){
			//System.out.println("TRUE");
		}
		
		//ally source
		if((this.source+"").charAt(1) == '2'){
			
			
		}
	}

}
