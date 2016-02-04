package bowling;

import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String input;
		String logfile;
		
		String menu = "Please enter test number[1-4]";
		System.out.println(menu);
		input = in.nextLine();
		if(input.compareTo("1")==0){
			logfile = "src/testInput/test1";
		}
		else if(input.compareTo("2")==0){
			logfile = "src/testInput/test2";
		}
		else if(input.compareTo("3")==0){
			logfile = "src/testInput/test3";
		}
		else if(input.compareTo("4")==0){
			logfile = "src/testInput/test4";
		}
		else {
			System.err.println("Invalid test choice");
			return;
		}
		logProcessor test = new logProcessor(logfile);
		System.out.println(test.getLog());
		System.out.println(test.getPlayersDetails());
	}

}
