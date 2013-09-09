import acm.program.*;
import acm.graphics.*;
import acm.util.*;
 
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import java.applet.*;

public class EnviroSquare extends GRect implements EnviroConstants{
	int preyNum;
	int predatorNum;
	int preyBreedTime;
	int predatorBreedTime;
	boolean haveFed;
	/*
	 * TIME BEFORE PREDATOR IS KILLED OFF. SUBTRACT 1 EVERY
	 * TURN THE PREDATORS DON'T FEED; RESET WHEN THEY FEED
	 */
	int survivalTime; 
	int xPos;
	int yPos;
	int feedTime; //TIME BEFORE PREDATOR FEEDS AGAIN
	
	public EnviroSquare(int x, int y){
		super(BOX_SIDE, BOX_SIDE);
		xPos = x;
		yPos = y;
		
		preyNum = 0;
		predatorNum = 0;
		
		preyBreedTime = (int) ((int)BASE_BREED_TIME*PREY_BREED_MULTIPLIER);
		//System.out.println("preyBreedTime = " + preyBreedTime);
		predatorBreedTime = (int) ((int)BASE_BREED_TIME*PREDATOR_BREED_MULTIPLIER);
		//System.out.println("predatorBreedTime = " + predatorBreedTime);
		
		survivalTime = BASE_SURVIVAL_TIME; // CODE FOR THIS AND FEEDTIME LATER!!!!!!!!!!!!!!!!!!!!!!!!
		feedTime = BASE_FEED_TIME;
		setColor(Color.WHITE);
		setFilled(true);
		
		haveFed = false;
	}
	
	/** TO SET THE NUMBER OF PREY **/
	public void setPreyNum(int n){
		preyNum = n;
	}
	
	/** TO SET THE NUMBER OF PREDATORS **/
	public void setPredatorNum(int n){
		predatorNum = n;
	}
	
	/** TO GET THE EnviroSquare's X-COORDINATE **/
	public int getXPos(){
		return xPos;
	}
	/** TO GET THE EnviroSquare's Y-COORDINATE **/
	public int getYPos(){
		return yPos;
	}
	
	/** TO GET THE NUMBER OF PREY **/
	public int getPreyNum(){
		return preyNum;
	}
	
	/** TO GET THE NUMBER OF PREDATORS **/
	public int getPredatorNum(){
		return predatorNum;
	}
	
	/** SUBTRACTS THE NUMBER OF PREY PRESENT FROM THE BREEDING TIME **/
	public void reducePreyBreedTime(){
		preyBreedTime -= preyNum;
	}
	
	/** RESETS THE PREY'S BREEDING TIME TO ITS ORIGINAL **/
	public void resetPreyBreedTime(){
		preyBreedTime = (int) ((int)BASE_BREED_TIME*PREY_BREED_MULTIPLIER);
	}
	
	/** RETURNS PreyBreedTime **/
	public int getPreyBreedTime(){
		return preyBreedTime;
	}
	
	/** SUBTRACTS THE NUMBER OF PREDATORS PRESENT FROM THE BREEDING TIME **/
	public void reducePredatorBreedTime(){
		predatorBreedTime -= predatorNum;
	}
	
	/** RESETS THE PREDATORS' BREEDING TIME TO ITS ORIGINAL **/
	public void resetPredatorBreedTime(){
		predatorBreedTime = (int) ((int)BASE_BREED_TIME*PREDATOR_BREED_MULTIPLIER);
	}
	
	/** RETURNS PredatorBreedTime **/
	public int getPredatorBreedTime(){
		return predatorBreedTime;
	}
	
	public int getFeedTime(){
		return feedTime;
	}
	public void reduceFeedTime(){
		feedTime --;
	}
	public void resetFeedTime(){
		feedTime = BASE_FEED_TIME;
	}
	
	public int getSurvivalTime(){
		return survivalTime;
	}
	public void reduceSurvivalTime(){
		survivalTime --;
	}
	public void resetSurvivalTime(){
		survivalTime = BASE_SURVIVAL_TIME;
	}
	
	public void resetColor(){
		if(preyNum == 0 && predatorNum == 0){
			this.setFillColor(prey0pred0);
		}else if(preyNum == 1 && predatorNum == 0){
			this.setFillColor(prey1pred0);
		}else if(preyNum == 2 && predatorNum == 0){
			this.setFillColor(prey2pred0);
		}else if(preyNum == 3 && predatorNum == 0){
			this.setFillColor(prey3pred0);
		}else if(preyNum == 4 && predatorNum == 0){
			this.setFillColor(prey4pred0);
		}else if(preyNum == 0 && predatorNum == 1){
			this.setFillColor(prey0pred1);
		}else if(preyNum == 1 && predatorNum == 1){
			this.setFillColor(prey1pred1);
		}else if(preyNum == 2 && predatorNum == 1){
			this.setFillColor(prey2pred1);
		}else if(preyNum == 3 && predatorNum == 1){
			this.setFillColor(prey3pred1);
		}else if(preyNum == 4 && predatorNum == 1){
			this.setFillColor(prey4pred1);
		}else if(preyNum == 0 && predatorNum == 2){
			this.setFillColor(prey0pred2);
		}else if(preyNum == 1 && predatorNum == 2){
			this.setFillColor(prey1pred2);
		}else if(preyNum == 2 && predatorNum == 2){
			this.setFillColor(prey2pred2);
		}else if(preyNum == 3 && predatorNum == 2){
			this.setFillColor(prey3pred2);
		}else if(preyNum == 4 && predatorNum == 2){
			this.setFillColor(prey4pred2);
		}else if(preyNum == 0 && predatorNum == 3){
			this.setFillColor(prey0pred3);
		}else if(preyNum == 1 && predatorNum == 3){
			this.setFillColor(prey1pred3);
		}else if(preyNum == 2 && predatorNum == 3){
			this.setFillColor(prey2pred3);
		}else if(preyNum == 3 && predatorNum == 3){
			this.setFillColor(prey3pred3);
		}else if(preyNum == 4 && predatorNum == 3){
			this.setFillColor(prey4pred3);
		}
	}
}
