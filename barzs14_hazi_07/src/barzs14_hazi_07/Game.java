package barzs14_hazi_07;

import java.util.Random;


public final class Game {
	private int[][] table;
	private int size;
	private int rnd;
	private int bombNum;
	
	public Game(int size, int rnd){
		this.table = new int[size][size];
		this.size = size;
		this.rnd = rnd;
		this.makeTable();
	}
	
	public int getBombNum(){
		return this.bombNum;
	}
	
	private void makeTable(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				table[i][j] = raffle();
			}
		}
		this.setTable();
	}
	
	public void setIt(int i, int j){
		this.table[i][j] = 0;
	}
	
	private int raffle(){
		int randomNum = new Random().nextInt(100);
		
		if (randomNum < rnd ){
			return -1; //bomb
		}
		
		return 0;
	}
	
	public boolean isWin(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(table[i][j] != -1 && table[i][j] != 0){
					return false;
				}
			}
		}
		return true;
	}
	
	private void setTable(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(table[i][j] == -1){
					bombNum++;
					if(((i+1) < size) && ((j+1) < size) && table[i+1][j+1] != -1){
						table[i+1][j+1]++;
					}
					if(((j+1) < size) && table[i][j+1] != -1){
						table[i][j+1]++;
					}
					if(((i+1) < size) && table[i+1][j] != -1){
						table[i+1][j]++;
					}				
					
					
					if((i > 0) && table[i-1][j] != -1){
						table[i-1][j]++;
					}
					if((j > 0) && table[i][j-1] != -1){
						table[i][j-1]++;
					}
					if((i > 0) && (j > 0) &&table[i-1][j-1] != -1){
						table[i-1][j-1]++;
					}
					
					if((j > 0) && ((i+1) < size) && table[i+1][j-1] != -1){
						table[i+1][j-1]++;
					}
					if((i > 0) && ((j+1) < size) && table[i-1][j+1] != -1){
						table[i-1][j+1]++;
					}
				}
			}
		}
	}

	public int getIt(int i, int j){
		
		return(table[i][j]);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(table[i][j] > -1)
					sb.append(" " + table[i][j] + " ");
				else
					sb.append(table[i][j] + " ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}


}
