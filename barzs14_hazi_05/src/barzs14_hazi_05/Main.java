package barzs14_hazi_05;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static final int BlackJack = 21;
	private static PlayerContainer<Player> inGame = new PlayerContainer<>();
	private static int numbersOfPLayer;
	private static Deck d;

	private static boolean haveWinner = false;
	
	private static Player newGamer(){
		String name = sc.next();
		return new Player(name);
	}
	
	private static void winTest(Player p){
		if(p.getScore() == BlackJack){
			p.plusWinNum();
			haveWinner = true;
		}
	}
	
	private static void actStance(Player p){
		System.out.println(p.toStringCards());
		if(p.isBlackjack())
			System.out.println(p.getName() + " is the Winner! Score: " + p.getScore() + "\n");
		else
			System.out.println(p.getName() + " Score: " + p.getScore() + "\n");
	}
	
	private static void runGame(){
		
		inGame.add(new Dealer("Dealer"));
		d = new Deck();
		Deck usedCards = new Deck(); 
		usedCards.makeEmpty();
		
		numbersOfPLayer = 0;
		System.out.println("Player number (max is 3): ");
		
		while(numbersOfPLayer <= 0 || numbersOfPLayer > 3){
			numbersOfPLayer = sc.nextInt();
			if(numbersOfPLayer <= 0 || numbersOfPLayer > 3){
				System.out.println("Error! Try again!");
			}
		}
		for(int i = 0; i < numbersOfPLayer; i++){
			System.out.println("What`s name is the " + (int)(i+1) + ". player: ");
			inGame.add(newGamer());
		}
		numbersOfPLayer++;
		boolean start = true;
		d.shuffling();
		
		while(start){
			
			for(int j = 0; j < 2; j++){
				for(int i = numbersOfPLayer-1; i >= 0; i--){
					inGame.get(i).add(((Dealer) inGame.get(0)).deal(d));
				}
			}
			for(int i = numbersOfPLayer-1; i >= 0; i--){
				actStance(inGame.get(i));
			}

			while(!haveWinner){
				for(int i = numbersOfPLayer-1; i >= 0; i--){
					if(inGame.get(i).getScore() < BlackJack)
						inGame.get(i).addToHand(((Dealer) inGame.get(0)).deal(d));
				}
				for(int i = numbersOfPLayer-1; i >= 0; i--){
					winTest(inGame.get(i));
					actStance(inGame.get(i));
				}

				if(haveWinner){
					for(int i = numbersOfPLayer-1; i >= 0; i--){
						actStance(inGame.get(i));	
						usedCards.setDeck(inGame.get(i).discard());
					}
				}
				else{
					int countBust = 0;
					for(int i = 0; i < numbersOfPLayer; i++){
						if(inGame.get(i).isBusted()){
							countBust++;
						}
					}
					for(int i = numbersOfPLayer-1; i >= 0 && countBust > numbersOfPLayer; i--){	
						usedCards.setDeck(inGame.get(i).discard());
					}
				}	
			}

			for(int i = 0; i < 30; i++)
				System.out.print("-");
			System.out.println("\n");
			
			System.out.println("Would you like to play another round? (y/n)");
			String again = "y";
			haveWinner = false;
			again = sc.next();
			if (again.equals("n")){
				start = false;
			}
		}
		

		
	}
	

	//allast csak ha valaki nyert es ha valaki veszitett. Egyebkent jonak tunik
	
	public static void main(String[] args) {

		try{
			runGame();
		}
		catch(InternalError e){
			System.out.println("Exception caught: " + e.getMessage());
		}finally{
			for(int i = 0; i < numbersOfPLayer; i++){
				System.out.println(inGame.get(i).getName() + " win " + inGame.get(i).getWinNum() + " times!");
			}
		}
		
		sc.close();
	}

}
