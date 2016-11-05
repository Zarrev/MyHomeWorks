package barzs14_hazi_05;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private boolean blackjack;
	private int winNum;
	private int score;
	private ArrayList<Card> cardsInHand;
	private boolean busted;
	public Player(String name) {
		super();
		this.name = name;
		this.winNum = 0;
		this.cardsInHand = new ArrayList<>();
		this.score = 0;
		this.blackjack = false;
		this.busted = false;
	}
	
	public boolean isBlackjack() {
		return blackjack;
	}
	
	public void add(Card c){
		this.cardsInHand.add(c);
	}
	
	public void addToHand(Card c){
		if(getScore() == 21){
			this.blackjack = true;
		}
		if(!this.blackjack)
			this.cardsInHand.add(c);
		else
			return;
	}
	
	public int getWinNum() {
		return winNum;
	}

	public void plusWinNum() {
		this.winNum++;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}

	public int getScore(){
		this.score = 0;
		int aceCount = 0;
		for ( Card card : cardsInHand ) {			
			switch(card.getRank()) {
				case ACE: 
					this.score += 11;
					aceCount++;
					break;
				case TWO: 
					this.score += 2; 
					break;
				case THREE: 
					this.score += 3; 
					break;
				case FOUR: 
					this.score += 4; 
					break;
				case FIVE: 
					this.score += 5; 
					break;
				case SIX:
					this.score += 6; 
					break;
				case SEVEN:
					this.score += 7; 
					break;
				case EIGHT:
					this.score += 8; 
					break;
				case NINE:
					this.score += 9; 
					break;
				case TEN: 
				case JACK: 
				case QUEEN: 
				case KING:
					this.score += 10; 
					break;
				default:
					score += 0;
					break;
			}
		}

		while ( this.score > Main.BlackJack && aceCount > 0){
			this.score -= 10;
			aceCount--;
		}
		
		if(score > 21)
			busted = true;
		
		return this.score;
	}
	
	public boolean isBusted(){
		return this.busted;
	}
	
	public ArrayList<Card> discard(){
		ArrayList<Card> tmp = this.cardsInHand;
		this.cardsInHand.clear();
		return  tmp;
	}
	
	public String toStringCards(){
		StringBuilder sb = new StringBuilder();
		sb.append("");
		
		for(int i = 0; i < cardsInHand.size(); i++){
			sb.append(cardsInHand.get(i).toString());
			sb.append("\t");
		}
		
		return sb.toString();
	}
}
