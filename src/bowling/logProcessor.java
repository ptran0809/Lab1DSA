package bowling;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logProcessor {
	private int numPlayer;
	private String filename;
	private ArrayList<Player> players = new ArrayList<Player>();

	public logProcessor(String filename) throws IOException {
		this.filename = filename;
		BufferedReader logFile = new BufferedReader(new FileReader(filename));
		String line;
		String[] token;
		int lineNumber = 0;
		line = logFile.readLine();
		lineNumber++;
		if (line.matches("\\d+")) {

			this.numPlayer = Integer.parseInt(line);
			this.createPlayer(this.numPlayer);
			line = logFile.readLine();
			lineNumber++;

			while ((line != null )) {
				if(line.trim().length() >0){
					String[] split = line.split(" ");
					if (split.length == this.numPlayer) {
						for (int i = 0; i < split.length; i++) {
							token = split[i].replace("[", "").replace("]", "").split(",");
							if (this.validatePinError(token, lineNumber)) {
								//System.out.println(this.players.get(i)==null);
								this.players.get(i).addFrame(token);							
							} else {
								System.err.println("Line" + lineNumber+": "+split[i]+"is not a valid frame");
								
							}
						}
					} else {
						System.err.println("Line" + lineNumber+": number of frame does not match number of player. NumFram="+split.length+", numPlayer="+this.numPlayer );
						
					}
	
					line = logFile.readLine();
					lineNumber++;
				}
			}
			for(Player p : this.players){
				p.finish();
			}
			

		} else {
			throw new numberPlayerError();
		}
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public static boolean isDigit(String s) {
		return s.matches(".d+");
	}

	private void createPlayer(int numPlayer) {
		for (int i = 0; i < numPlayer; i++) {
			this.players.add(new Player(i));
		}
	}

	private boolean isPin(String s) {

		return (s.matches("\\d") || s.compareTo("/") == 0||s.compareTo("x")==0||s.compareTo("X")==0);
	}

	private boolean isPin(String[] s) {
		for (int i = 0; i < s.length; i++) {
			if (!isPin(s[i])) {
				return false;
			}
		}
		return true;
	}
	
	public String getLog() throws IOException{
		BufferedReader logFile = new BufferedReader(new FileReader(this.filename));
		String line;
		String str;
		line = logFile.readLine();
		str = line;
		while ((line != null )) {
			if(line.trim().length() >0){
				line = logFile.readLine();
				str = str + "\n"+ line;
			}
		}
		
		return str;
		
	}
	
	private boolean validatePinError(String[] token, int lineNumber) {
		return (((lineNumber < 11) && ((token.length == 2)||(token.length==1) && (token[0].compareTo("x")==0||token[0].compareTo("X")==0)) || (lineNumber == 11) && (token.length >= 2)&& (token.length <= 3))&&isPin(token)
				);
	}
	
	public String getPlayersDetails(){
		String str="";
		for(Player p : this.players){
			
			str = str + p.toString() + "\n";
		}
		return str;
	}

	
}
