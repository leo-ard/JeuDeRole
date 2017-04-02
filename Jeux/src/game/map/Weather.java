package game.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.core.GamePane;

public class Weather {
	public static final int CLEAR = 0;
	public static final int RAINNING = 1;
	
	//---- CUSTOMSABLE ----//
	private final double iFinal = 400;
	
	//RAINNING
	private final int nbRainInRainning = 500;
	private final Color rainning = new Color(0,0,100,30);
	
	//CLEAR
	private final int nbRainInClear = 0;
	private final Color clear = new Color(0,50,50,20);
	//---------------------//
	
	private boolean isChanging;
	private double iForChange;
	
	
	private ArrayList<Rain> rain;
	private int weather;
	private int weatherToChange;
	
	public Weather(int weather){
		this.weather = weather;
		rain = new ArrayList<Rain>();
		isChanging = false;
	}
	
	public void update(){
		if(!this.isChanging){
			if(this.weather == 0){
				GamePane.filtre = clear;
				this.rain.clear();
			}
			if(this.weather == 1){
				GamePane.filtre = rainning;
				for(int i = 0; i < this.nbRainInRainning; i++){
					this.rain.add(new Rain(Color.blue));
				}
				
				for(int i = 0; i < rain.size(); i++){
					if(rain.get(i).update()== true){
						rain.remove(i);
					}
				}
			}
		}
		else{
			if(transition()){
				this.isChanging = false;
				weather = weatherToChange;
			}
		}
	}
	
	public void draw(Graphics2D g){
			for(int i = 0; i < rain.size(); i++){
				rain.get(i).draw(g);
			}
	}
	
	public void change(int w){
		if(this.weather == w||isChanging)
			return;
		this.weatherToChange = w;
		isChanging = true;
		iForChange =0;
		
	}
	
	public boolean transition(){
		iForChange++;
		Color c1;
		Color c2;
		int nbRain1;
		int nbRain2;
		if(this.weather == 0 && this.weatherToChange == 1){
			c1 = clear;
			c2 = rainning;
			nbRain1 = this.nbRainInClear;
			nbRain2 = this.nbRainInRainning;
		}
		else if(this.weather == 1 && this.weatherToChange == 0){
			c1 = rainning;
			c2 = clear;
			nbRain1 = this.nbRainInRainning;
			nbRain2 = this.nbRainInClear;
		}
		else
			return true;
		
		GamePane.filtre = new Color((int)(c1.getRed()+(iForChange/iFinal)*(c2.getRed()-c1.getRed())),(int)(c1.getGreen()+(iForChange/iFinal)*(c2.getGreen()-c1.getGreen())),(int)(c1.getBlue()+(iForChange/iFinal)*(c2.getBlue()-c1.getBlue())), (int)(c1.getAlpha()+(iForChange/iFinal)*(c2.getAlpha()-c1.getAlpha())));
		for(int i = 0; i < nbRain1+(iForChange/iFinal)*(nbRain2-nbRain1); i++){
			this.rain.add(new Rain(Color.blue));
		}
		
		for(int i = 0; i < rain.size(); i++){
			if(rain.get(i).update()== true){
				rain.remove(i);
			}
		}
		if(iForChange > iFinal){
			return true;
		}
		return false;
	}

}
