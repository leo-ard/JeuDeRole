package game.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import game.map.Block;
import game.map.Weather;

public class Listener implements KeyListener, MouseListener, MouseWheelListener{

	public boolean WOrUp;
	public boolean AOrLeft;
	public boolean SOrDown;
	public boolean DOrRight;
	
	public boolean LEFT_CLICK;
	public boolean RIGHT_CLICK;
	
	public boolean SHIFT;
	
	public int whellRotation = 0;
	
	public boolean F1;
	public boolean F2;
	public boolean F3;
	public boolean F4;
	public boolean F5;
	public boolean F6;
	public boolean F7;
	public boolean F8;
	public boolean F9;
	public boolean F10;
	public boolean F11;
	public boolean F12;
	
	public boolean E;
	public boolean Q;
	public boolean SPACE;
	
	
	public boolean ESC;
	
	public Listener(){
		super();
	}
	
	//---- Mouse Wheel Listener ----//
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		whellRotation = e.getWheelRotation();
	}

	//---- Mouse Listener ----//
	@Override
	public void mousePressed(MouseEvent e) {
		//Right Click
		if(e.getButton() == MouseEvent.BUTTON1){
			RIGHT_CLICK = true;
			//GamePane.player.getArme().canshoot = true;
		}
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON3){
			LEFT_CLICK = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Right Click
		if(e.getButton() == MouseEvent.BUTTON1){
			RIGHT_CLICK = false;
		}
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON3){
			LEFT_CLICK = false;
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	//---- Key Listener ----//

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W||e.getKeyCode() == KeyEvent.VK_UP){
			WOrUp = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A||e.getKeyCode() == KeyEvent.VK_LEFT){
			AOrLeft = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S||e.getKeyCode() == KeyEvent.VK_DOWN){
			SOrDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D||e.getKeyCode() == KeyEvent.VK_RIGHT){
			DOrRight = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			SHIFT = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F1){
			F1 = true;
			Frame.gp.pause();
		}
		if(e.getKeyCode() == KeyEvent.VK_F2){
			F2 = true;
			Frame.gp.resume();
		}
		if(e.getKeyCode() == KeyEvent.VK_F3){
			F3 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F4){
			F4 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F5){
			F5 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F6){
			F6 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F7){
			F7 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F8){
			F8 = true;
			GamePane.map.getWeather().change(Weather.CLEAR);
		}
		if(e.getKeyCode() == KeyEvent.VK_F9){
			F9 = true;
			GamePane.map.getWeather().change(Weather.RAINNING);
		}
		if(e.getKeyCode() == KeyEvent.VK_F10){
			F10 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F11){
			F11 = true;
			Block.hauteur--;
		}
		if(e.getKeyCode() == KeyEvent.VK_F12){
			F12 = true;
			Block.hauteur++;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			ESC = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			E = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			Q = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			SPACE = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W||e.getKeyCode() == KeyEvent.VK_UP){
			WOrUp = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A||e.getKeyCode() == KeyEvent.VK_LEFT){
			AOrLeft = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S||e.getKeyCode() == KeyEvent.VK_DOWN){
			SOrDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D||e.getKeyCode() == KeyEvent.VK_RIGHT){
			DOrRight = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			SHIFT = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F1){
			F1 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F2){
			F2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F3){
			F3 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F4){
			F4 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F5){
			F5 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F6){
			F6 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F7){
			F7 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F8){
			F8 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F9){
			F9 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F10){
			F10 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F11){
			F11 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F12){
			F12 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			ESC = false;
			Frame.pPause.refresh();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_E){
			E = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			Q = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			SPACE = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public int getWhellRotation(){
		int x = whellRotation;
		whellRotation = 0;
		return x;
	}

}
