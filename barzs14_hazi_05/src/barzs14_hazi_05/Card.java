package barzs14_hazi_05;

public final class Card {
	public enum Rank {
	    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;	    
	}
	
	public enum Symbol {
		HEARTS, DIAMONDS, SPADES, CLUBS;
	}
	
	private Rank rank;
	private Symbol symbol;
	
	public Card(Rank rank, Symbol symbol) {
		super();
		this.rank = rank;
		this.symbol = symbol;
	}
	
	public Rank getRank() { 
		return this.rank; 
	}
	public Symbol getSuit() {
		return this.symbol; 
	}

	@Override
	public String toString() {
		return this.rank.toString() + " " + this.symbol.toString() ;
	}
}
