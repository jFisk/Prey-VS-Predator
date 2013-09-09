import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import java.applet.*;

/*
 * POSSIBLE EXTENSIONS!!!!!!!!!!!!!!!!!!!!
 * 1. Make EnviroSquare a GCompound of multiple GRects (with same information); 1 for each
 * 	  animal-type, so coloring overlap is easier, and so other species can be added
 * 2. Make the predator's breeding yield depend on if it's fed or not //half-check
 * 3. Code for situations of overallocation of space
 * 4. Possibly switch order of breeding/eating?
 * 5. Match the constants to rabbit/fox
 * 6. Add in a searchForPrey method that allows predators in boxes without prey in them to
 *    move in search of prey, rather than wait there until death (similar checking to
 *    addPredator method)
 */

public class TwoTypeEnvironment extends GraphicsProgram implements EnviroConstants{
	private EnviroSquare[][] enviroGrid;
	private int preyStart;
	private int predatorStart;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	int numRows = APPLICATION_HEIGHT/BOX_SIDE;
	int numCols = APPLICATION_WIDTH/BOX_SIDE;
	private boolean preyAlive;
	private boolean predatorAlive;

	public void init(){
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);

		setUpEnvironment();
		plantInitialAnimals();
		addEnviroSquares();
		
		preyAlive = false;
		predatorAlive = false;
	}
	public void setUpEnvironment(){
		//SET UP THE LINES FOR THE GRID
		System.out.println("rows: " + numRows + "; cols: " + numCols);
		/*
		for(int i = 0; i <= numCols; i++){
			add(new GLine(i*BOX_SIDE, 0, i*BOX_SIDE, APPLICATION_HEIGHT));
		}
		for(int j = 0; j <= numRows; j++){
			add(new GLine(0, j*BOX_SIDE, APPLICATION_WIDTH, j*BOX_SIDE));
		}
		*/

		enviroGrid = new EnviroSquare[numCols][numRows];
		for(int i = 0; i < numCols; i++){
			for(int j = 0; j < numRows; j++){
				EnviroSquare curr = new EnviroSquare(i, j);
				enviroGrid[i][j] = curr;
			}
		}
	}

	public void plantInitialAnimals(){
		for(int i = 0; i < 20; i++){
			//int initPreyX = rgen.nextInt(numCols);
			//int initPreyY = rgen.nextInt(numRows);
			int initX = rgen.nextInt(numCols);
			int initY = rgen.nextInt(numRows);
			preyStart = rgen.nextInt(2, 4);
			enviroGrid[initX][initY].setPreyNum(preyStart);
			enviroGrid[initX][initY].resetColor();
			System.out.println("Number of prey at " + initX + ", " + initY + " is " + preyStart);

			//int initPredatorX = rgen.nextInt(numCols);
			//int initPredatorY = rgen.nextInt(numRows);
			predatorStart = rgen.nextInt(2, 3);
			enviroGrid[initX][initY].setPredatorNum(predatorStart);
			enviroGrid[initX][initY].resetColor();
			System.out.println("Number of predators at " + initX + ", " + initY + " is " + predatorStart);
		}
	}

	public void addEnviroSquares(){
		for(int i = 0; i < numCols; i++){
			for(int j = 0; j < numRows; j++){
				//System.out.println("Number of prey at " + i + ", " + j + " is " + enviroGrid[i][j].getPreyNum());
				enviroGrid[i][j].resetColor();
				add(enviroGrid[i][j], i*BOX_SIDE, j*BOX_SIDE);
			}
		}
	}

	public void run(){
		System.out.println("MADE IT THIS FAR.");
		int cnt = 0;
		while(true){
			// THE BREEDING PHASE
			checkForBreeding();
			if(BOX_SIDE >= 10){
				addEnviroSquares();
				pause(PAUSE_TIME);
			}
			//waitForClick();
			// THE PREDATION PHASE
			checkPredation();
			addEnviroSquares();
			
			if(preyAlive || predatorAlive){
				preyAlive = false;
				predatorAlive = false;
			}else{
				break;
			}
			cnt++;
			pause(PAUSE_TIME);
			//waitForClick();
		}
		System.out.println("Ecosystem survived " + cnt + " iterations");
	}
	public void checkForBreeding(){
		for(int i = 0; i < numCols; i++){
			for(int j = 0; j < numRows; j++){
				checkPreyBreeding(i, j);
				checkPredatorBreeding(i, j);
			}
		}
	}
	public void checkPreyBreeding(int currX, int currY){
		if(!preyAlive && enviroGrid[currX][currY].getPreyNum() > 0){
			preyAlive = true;
		}
		if(enviroGrid[currX][currY].getPreyNum() >= 2){
			enviroGrid[currX][currY].reducePreyBreedTime();
			if(enviroGrid[currX][currY].getPreyBreedTime() <= 0){
				addPrey(currX, currY);
				enviroGrid[currX][currY].resetPreyBreedTime();
			}
		}
	}
	public void addPrey(int currX, int currY){
		for(int i = 0; i < PREY_BREED_YIELD; i++){
			if(enviroGrid[currX][currY].getPreyNum() < PREY_CAPACITY){
				enviroGrid[currX][currY].setPreyNum(enviroGrid[currX][currY].getPreyNum() + 1);
			}else{
				int newX;
				int newY;
				int goodX = numCols;
				int goodY = numRows;
				for(int j = -1; j <= 1; j++){
					for(int k = -1; k <= 1; k++){
						newX = currX + j;
						newY = currY + k;
						if(newX >= 0 && newX < numCols && newY >= 0 && newY < numRows && !(j == 0 && k == 0)){
							if(enviroGrid[newX][newY].getPreyNum() < PREY_CAPACITY
									&& (goodX == numCols || enviroGrid[newX][newY].getPreyNum() > enviroGrid[goodX][goodY].getPreyNum())){
								goodX = newX;
								goodY = newY;
							}
						}
					}
				}
				if(goodX != numCols){
					enviroGrid[goodX][goodY].setPreyNum(enviroGrid[goodX][goodY].getPreyNum() + 1);
				}
			}
		}
	}

	public void checkPredatorBreeding(int currX, int currY){
		if(!predatorAlive && enviroGrid[currX][currY].getPredatorNum() > 0){
			predatorAlive = true;
		}
		if(enviroGrid[currX][currY].getPredatorNum() >= 2){
			enviroGrid[currX][currY].reducePredatorBreedTime();
			if(enviroGrid[currX][currY].getPredatorBreedTime() <= 0 && enviroGrid[currX][currY].haveFed){
				addPredator(currX, currY);
				enviroGrid[currX][currY].resetPredatorBreedTime();
				enviroGrid[currX][currY].haveFed = false;
			}
		}
	}
	public void addPredator(int currX, int currY){
		for(int i = 0; i < PREDATOR_BREED_YIELD; i++){
			if(enviroGrid[currX][currY].getPredatorNum() < PREDATOR_CAPACITY){
				enviroGrid[currX][currY].setPredatorNum(enviroGrid[currX][currY].getPredatorNum() + 1);
			}else{
				int newX;
				int newY;
				int goodX = numCols;
				int goodY = numRows;
				for(int j = -1; j <= 1; j++){
					for(int k = -1; k <= 1; k++){
						newX = currX + j;
						newY = currY + k;
						if(newX >= 0 && newX < numCols && newY >= 0 && newY < numRows && !(j == 0 && k == 0)){
							if(enviroGrid[newX][newY].getPredatorNum() < PREDATOR_CAPACITY
									&& (goodX == numCols || enviroGrid[newX][newY].getPreyNum() > enviroGrid[goodX][goodY].getPreyNum())){
								goodX = newX;
								goodY = newY;
							}
						}
					}
				}
				if(goodX != numCols){
					enviroGrid[goodX][goodY].setPredatorNum(enviroGrid[goodX][goodY].getPredatorNum() + 1);
				}
			}
		}
	}

	public void checkPredation(){
		for(int i = 0; i < numCols; i++){
			for(int j = 0; j < numRows; j++){
				//System.out.println("MERRRRRRRRR");
				if(enviroGrid[i][j].getPredatorNum() > 0){
					enviroGrid[i][j].reduceFeedTime();
					
					if(enviroGrid[i][j].getFeedTime() <= 0 && enviroGrid[i][j].getPreyNum() > 0){
						enviroGrid[i][j].setPreyNum(enviroGrid[i][j].getPreyNum() - enviroGrid[i][j].getPredatorNum());
						enviroGrid[i][j].haveFed = true;
						if(enviroGrid[i][j].getPreyNum() <= 0){
							enviroGrid[i][j].setPreyNum(0);
							enviroGrid[i][j].resetPreyBreedTime();
						}
						enviroGrid[i][j].resetFeedTime();
						enviroGrid[i][j].resetSurvivalTime();
						
					}else{
						enviroGrid[i][j].reduceSurvivalTime();
						if(enviroGrid[i][j].getSurvivalTime() <= 0){
							enviroGrid[i][j].haveFed = false;
							enviroGrid[i][j].setPredatorNum(0);
							enviroGrid[i][j].resetPredatorBreedTime();
							enviroGrid[i][j].resetFeedTime();
							enviroGrid[i][j].resetSurvivalTime();
						}
					}
				}
			}
		}
	}
	/*
	 * SIMILAR CODE TO WHAT I'LL NEED LATER
	 * 
				GRect colorBox = new GRect(BOX_SIDE, BOX_SIDE);
				colorBox.setFilled(true);
				colorBox.setColor(new Color(0, 210, 255, 150));
				colorBox.setLocation(i*BOX_SIDE, j*BOX_SIDE);
				add(colorBox);
	 */
}
