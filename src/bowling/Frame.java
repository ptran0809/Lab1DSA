package bowling;

public class Frame {
	
	private String[] frameLog;
	private Frame nextFrame;
	private int score;
	public String[] getFrameLog() {
		return frameLog;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setFrameLog(String[] frameLog) {
		this.frameLog = frameLog;
	}

	public Frame(String[] frameLog) {
		super();
		this.frameLog = frameLog;
		this.nextFrame = null;
		this.score = 0;
		
	}
	
	public boolean isStrike(){
		for(String s:this.getFrameLog()){
			if(s.contains("x") || s.contains("X") ) return true;
		}
		return false;
	}
	

	private void calculateNotStrikeOrSpare(){
		
		for(String s: this.frameLog){
			this.score = this.score + Frame.convertToScore(s);
			
		}
	}
	
	public boolean isSpare(){
		for(String s:this.getFrameLog()){
			if(s.contains("/")) return true;
		}
		return false;
	}
	
	private static int convertToScore(String s){
		if((s.compareTo("X")==0)||(s.compareTo("x")==0)||(s.compareTo("/")==0)){
			return 10;
		}
		else{
			return Integer.parseInt(s);
		}
		
	}
	
	protected int getFirstBall(){
		return Frame.convertToScore(this.frameLog[0]);
	}
	protected int getTwoBall(){
		return  Frame.convertToScore(this.frameLog[0]) +  Frame.convertToScore(this.frameLog[1]);
	}
	
	
	protected void calculateLastFrame(){
		if(this.isSpare()){
			this.score = 10 + Frame.convertToScore(this.frameLog[2]);
		}
		else calculateNotStrikeOrSpare();
	}
	
	protected void calculate(int nextFrameScore,int nextFirstBall){
		if(this.isSpare()){
			this.score = 10 + nextFirstBall;
		}
		else if(this.isStrike()){
			this.score = 10 + nextFrameScore;
		}
		else{
			this.calculateNotStrikeOrSpare();
		}
	}
	protected Frame getNextFrame() {
		return nextFrame;
	}

	protected void setNextFrame(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}
		

	public String toString(){
		return String.join(",", this.frameLog);
	}
}
