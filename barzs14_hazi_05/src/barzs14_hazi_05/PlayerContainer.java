package barzs14_hazi_05;

import java.util.ArrayList;

public final class PlayerContainer<T extends Player>{

	private ArrayList<T> container;
	private int maxSize = 4;
	public PlayerContainer() {
		this.container = new ArrayList<>(4);
	}
	public void add(T p){
		if(this.container.size() == maxSize){
			System.out.println("Can not add more player!");
			return;
		}
		this.container.add(p);
		
	}
	
	public Player get(int i){
		return this.container.get(i);
	}
}
