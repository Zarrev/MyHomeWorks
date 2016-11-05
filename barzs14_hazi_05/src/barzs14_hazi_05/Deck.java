package barzs14_hazi_05;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>(52);

		for(Card.Rank r : Card.Rank.values()) 
			for(Card.Symbol s : Card.Symbol.values()) 
				deck.add(new Card(r, s));
	}
	
	public void shuffling() {
		
		Collections.shuffle(deck);
	}
	
	public Card draw() {
		if (hasCards())
			return deck.remove(0);
		return new Card(Card.Rank.ACE, Card.Symbol.HEARTS);
	}
	
	public boolean hasCards() {
		return 0 < deck.size();
	}
	
	public void makeEmpty(){
		this.deck.clear();
	}
	
	public void add(Card c){
		deck.add(c);
	}
	
	public void setDeck(ArrayList<Card> c){
		for(int i = 0; i < c.size(); i++){
			deck.add(c.get(i));
		}
	}
}
