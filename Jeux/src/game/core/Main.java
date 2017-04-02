package game.core;

import game.saves.GameSave;

public class Main {
	
	public static Frame windows;
	public static GameSave gameSave;
	public static Listener l;
	
	//@SuppressWarnings("unused")
	public static void main(String Args[]){
		
		l = new Listener();
		
		windows = new Frame();
		
			
		gameSave = new GameSave();
		gameSave.load();
		
		
	}

}
