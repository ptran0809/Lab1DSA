package bowling;

import java.util.ArrayList;

public class Player {
	private int id;
	private ArrayList<String[]> logs ;
	private int score;
	private int numberSpare;
	private int numberStrike;
	public Player(int id) {
		super();
		this.id = id;
	}
	
	public String toString(){
		return "Player :"+this.id 
				+"\nScore: " + this.score
				+ "\nSpare"+ this.numberSpare+" times"
				+ "\nStrike "+ this.numberStrike+ "times";
	}
	public void addLog(String[] log ){
		this.logs.add(log);
	}
	

}
 