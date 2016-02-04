package bowling;

import java.util.*;

public class Player {
	private int id;
	private Frame frames = null;
	private int score =0;
	private int srikeNum =0;
	private int spareNum =0;
	private boolean isFinish = false;
	public Player(int playerNum) {
		setplayerNum(playerNum);
	}

	public int getplayerNum() {
		return id;
	}

	
	public void setplayerNum(int id) {
		this.id = id;
	}

	
	
	public Frame getFrames() {
		return frames;
	}

	
	public void addFrame(Frame frame) {
		frame.setNextFrame(this.frames);
		this.frames = frame;
	}
	
	public void addFrame(String[] frame){
		this.addFrame(new Frame(frame));
	}		
	
	protected void setScore() {
		int lastTwoBall = 0;
		int lastFirstBall = 0;
		Frame curr;
		Frame last;
		curr = this.frames;
		if(curr != null){
			curr.calculateLastFrame();
			this.score = this.score + curr.getScore();
			lastTwoBall = curr.getTwoBall();
			lastFirstBall = curr.getFirstBall();
			last = curr;
			curr = curr.getNextFrame();
			
			while(curr != null){
				curr.calculate(lastTwoBall, lastFirstBall);
				this.score = this.score + curr.getScore();
				
				lastTwoBall = this.getTwoBall(curr, last);
				lastFirstBall = curr.getFirstBall();
				last = curr;
				curr = curr.getNextFrame();
				
			}
		}
		
		
	}
	protected void setSrikeNum(){
		Frame curr;
		curr = this.frames;	
		if(curr != null){
			curr = this.frames;		
			for(String s: curr.getFrameLog()){
				if(s.contains("x")||s.contains("X")){
					this.srikeNum ++;
				}
			}
			curr = curr.getNextFrame();
			while(curr != null){
				if(curr.isStrike())
					this.srikeNum ++;
				curr = curr.getNextFrame();
			}
		}
	}
	protected void setSpareNum(){
		Frame curr;
		curr = this.frames;		
		while(curr != null){
			if(curr.isSpare())
				this.spareNum ++;
			curr = curr.getNextFrame();
		}
		
	}

	protected void finish(){
		if(!this.isFinish){
			this.setScore();
			this.setSpareNum();
			this.setSrikeNum();
			this.isFinish = true;
		}
	}

	protected int getTwoBall(Frame curr,Frame last){
		int r =0;
		if(curr.isStrike()){
			r = curr.getFirstBall() + last.getFirstBall();
		}
		else{
			r= curr.getTwoBall();
		}
		return r;
	}
	

	public String toString(){
		String str;
		
		str = "Player "+this.id+": Score = "+this.score+", striked " +this.srikeNum 
			+" times and spared "+ this.spareNum+" times";
		return str;
	}
	
	public void printout(){
		Frame curr;
		curr = this.frames;		
		while(curr != null){
			System.out.println(curr.getScore());
			curr = curr.getNextFrame();
		}
	}
	public void printALL(){
		Frame curr;
		curr = this.frames;		
		while(curr != null){
			System.out.println(curr.toString());
			curr = curr.getNextFrame();
		}
	}
	
}
