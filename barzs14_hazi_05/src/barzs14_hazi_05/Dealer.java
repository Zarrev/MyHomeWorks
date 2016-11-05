package barzs14_hazi_05;

public final class Dealer extends Player {

	public Dealer(String name) {
		super(name);
	}

	public Card deal(Deck d){
		if(!d.hasCards())
			throw new InternalError("Out of cards! Game over!");
		
		return d.draw();
	}
}
