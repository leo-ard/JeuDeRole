package game.editeur;

public abstract class Command {
	private String Name;
	private char Act;
	private String args;
	
	public Command(String name, char act){
		
	}
	
	private void use(){
		System.out.println("Not defined");
	}
	
	public void changeArgs(String args){
		this.args = args;
	}

}
