package barzs14_hazi_02;
/// stackoverflowot hasznaltam tobbszor is (pl az enum random kivalasztasnal)
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

	private static Scanner sc = new Scanner(System.in);
	
	private enum FourLegs{
		Szarvasmarha, Bika, Tehen, Lo, Men, Csodor, Kanca, Csiko, Paripa,
		Juh, Kos, Barany, Sertes, Diszno, Malac, Nyul
	}
	
	private enum Poultry{
		Tyuk, Csirke, Jerce, Kakas, Kacsa, Liba, Furj, Pulyka, Gyongytyuk
	}
	
	private static void logOut(String s) throws IOException{
		try{
			FileWriter fw = new FileWriter("log.txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.println(s);
			pw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static String logS = new String();
	
	private static void logStringMake(String s){
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(s);
		sb.append("\n");
		logS += sb.toString();		
	}
	
	private static String makeResult(){
		String s = new String();		
		Random i = new Random();
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		
		int randomNum = i.nextInt(100);
		
		if (randomNum > 40){
			int pick = new Random().nextInt(Poultry.values().length);
			sb.append(Poultry.values()[pick].toString());
		}
		else{
			int pick = new Random().nextInt(FourLegs.values().length);
			sb.append(FourLegs.values()[pick].toString());
		}
		
		s = sb.toString();
		
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		boolean win = false;
		String winS = makeResult().toLowerCase();
		Random i = new Random();
		int rand = i.nextInt(100)+1;
		
		String s = new String("Udvozlom szepen ebben az izgalmas jatekban, a szam es allatkitalaloban! Remelem elvezni fogja! :)");
		System.out.println(s);
		logStringMake(s);
		System.out.println(rand + " " + winS);
		
		while(!win){
			s = "Kerem szepen adjon meg egy szamot es egy allatot szokozzel elvalasztva!";
			System.out.println(s);
			logStringMake(s);
			s = sc.nextLine().toLowerCase();
			logStringMake(s);
			
			Matcher im = Pattern.compile("\\d+").matcher(s);
			Matcher sm = Pattern.compile("[a-z]\\w+").matcher(s);
			Integer iok = -1;
			String fok = "";
			if (im.find()){
				iok = Integer.parseInt(im.group());
			}
			if (sm.find()) {
				fok = sm.group();
			}
			
			StringBuilder ans = new StringBuilder();
			ans.append("");
			boolean intBool = false, strBool = false;

			if(iok <= rand-10){
				ans.append("En sokkal tobbre ");
			}
			else if(iok < rand){
				ans.append("En tobbre ");
			}
			else if(iok >= rand+10){
				ans.append("En sokkal kevesebbre ");
			}
			else if(iok > rand){
				ans.append("En kevesebbre ");
			}
			else{
				intBool = true;
				ans.append("Pont ennyire ");
			}
			
			if(fok.equals(winS)){
				if (intBool){
					ans.append("es " + fok + "ra/re gondoltam!");
				}
				else{
					ans.append("de " + fok + "ra gondoltam");
				}
				strBool = true;
			}
			else{
				if (intBool){
					ans.append("de nem " + fok + "ra/re gondoltam!");
				}
				else{
					ans.append("es nem " + fok + "ra/re gondoltam!");
				}
			}
			
			
			s = ans.toString();
			System.out.println(s);
			logStringMake(s);
			
			if (intBool && strBool){
				win = true;
				s = "Gratulálok kitaláltad! A jateknak vege!";
				System.out.println(s);
				logStringMake(s);
			}
				
		}		
		
		sc.close();
		logOut(logS);
	}

}
