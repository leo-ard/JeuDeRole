package game.champions.personnages;

import game.abilities.SkillShot;
import game.armes.Slash;
import game.champions.Champions;
import game.champions.Effects;
import game.champions.Mana;
import game.core.GamePane;
import game.core.Settings;
import game.mobs.HitBox;

public class Lore extends Champions{
	
	public String lastAbilityCast;
	
	public int E_cooldown = 2000;
	public long E_lastPressed;

	public Lore(int x, int y) {
		super(x, y);
		SPE = 10;
		MAX_HP = 100;
		HP = MAX_HP;
		ATT_SPE = 200;
		haveMana = true;
		MANA = new Mana(100,1);
		
		base_SPE = SPE;
		base_MAX_HP = HP;
		base_HP = MAX_HP;
		base_ATT_SPE = ATT_SPE;
		// TODO Auto-generated constructor stub
	}
	
	public boolean update(){
		MANA.update();
		super.update();
		GamePane.map.hitBox.add(new HitBox(HitBox.RECTANGLE,this, HitBox.ALLYCHAMPION));
		if(GamePane.l.E){
			this.cc_cast(100, "E");
		}
		return false;
	}
	

	@Override
	protected void aa_options() {
		if(this.isEffectExisting("aa stacks")&&(Integer)this.getEffect("aa stacks").info[0]==1){
			GamePane.map.slashs.add(new Slash(this, false, 100,90,Settings.attackSpeedAnimation));
			this.getEffect("aa stacks").info[0] = ((Integer) this.getEffect("aa stacks").info[0])+1;
			this.getEffect("aa stacks").refresh();
		}
		else if(this.isEffectExisting("aa stacks")&&(Integer)this.getEffect("aa stacks").info[0]==2){
			GamePane.map.slashs.add(new Slash(this, true, 125,30,Settings.attackSpeedAnimation));
			this.removeEffect("aa stacks");
			this.effects.add(new Effects("Tired", "lorsque la troisieme attaque est mise",this,1,600,new boolean[]{false,false,true},null,null));
		}
		else{
			GamePane.map.slashs.add(new Slash(this, true, 100,90,Settings.attackSpeedAnimation));
			this.effects.add(new Effects("aa stacks","Effect for auto-attack",this, 0, 2000,new boolean[]{false,false,false},null, new Object[]{new Integer(1)}));
		}
		
	}
	
	protected void cc_options(){
		state = "null";
		
		if(this.cc_abilityName.equals("E")){
			E_lastPressed = System.currentTimeMillis();
			//int dMG, int pOW, int dEF, int hP, int mAX_HP, int sPE, int aTT_SPE, Mana m)
			//this.effects.add(new Effects("Test", "Test",this, -1, 500, new boolean[]{false,false,false}, new Stats(0, 0, 0, 100, 100, 5, 100, null), null));
		}
		
		
		
		GamePane.map.ability.add(new SkillShot((int)this.x, (int)this.y,50,50,GamePane.getAngleOfTheMouseAndThePlayer(), 10));
	}

	protected boolean cc_cooldownCheck() {
		if(this.cc_abilityName.equals("E")){System.out.println(System.currentTimeMillis()-E_lastPressed);
			if(System.currentTimeMillis()-E_lastPressed<E_cooldown)
				
				return true;
		}
		
		return false;
	}

}
