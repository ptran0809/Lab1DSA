package bowling;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logProcessor {
    private int numPlayer;
    private ArrayList<Player> players = new ArrayList<Player>();

    public logProcessor(String filename) throws IOException{
	BufferedReader logFile = new BufferedReader(new FileReader(filename));
	String line;
	String[] token;
	int lineNumber =0;
	line = logFile.readLine();
	lineNumber++;
	if(logProcessor.isDigit(line)){
	    this.numPlayer = Integer.parseInt(line);
	    this.createPlayer(this.numPlayer);
	    line = logFile.readLine();
	    lineNumber++;
	    while(line != null){
		String[] split = line.split(" ");
		if(split.length == this.numPlayer){
		   for(int i=0;i<split.length;i++){
		       token = split[i].replace("[", "").replace("]","").split(",");
		       if(this.validatePinError(token, lineNumber)){			       
			   this.players.get(i).addLog(token);
		       }
		       else{
			   throw new PinErrorException(lineNumber);
		       }
		   }
		}
		else{
		    throw new NumberEntryException(lineNumber);
		}
		line = logFile.readLine();
	    }
	}
	else{
	    throw new numberPlayerError();
	}
    }
    public static boolean isDigit(String s){
	return s.matches(".d+");
    }
    private void createPlayer(int numPlayer){
	for(int i=0;i<numPlayer;i++){
	    this.players.add(new Player(i));
	}
    }
    private boolean isPin(String s){
	return s.matches("[.d/]]");
    }
    private boolean isPin(String[] s){
	for(int i=0;i<s.length;i++){
	    if(!isPin(s[i])){
		return false;
	    }
	}
	return true;
    }
    private boolean validatePinError(String[] token,int lineNumber){
	 return (((lineNumber < 10 )&&(token.length ==2)
		       || (lineNumber == 10 )&&(token.length ==3))
		 && isPin(token));
	     
    }

}
