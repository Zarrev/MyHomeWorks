package itk.ppke.java.first.homework;

import java.util.Scanner;
import java.util.Vector;

public class SparseMatrix {
	
	// ahogy kapja az ertekeket novelek egy int i es int j valtozot
	//es amikor nem nulla a szam lementem az i-t es a j-t is es az erteket is egy Node-ba
	//majd mutatok a kovetkezo elemre
	
	private Vector<int[]> A = new Vector<>();
	private int m,n;
	
	public SparseMatrix(int row, int column) {
		this.m = column;
		this.n = row;
		readM();
	}
	
	public SparseMatrix() {
		this.n = 3;
		this.m = 3;
		int[] a = {1,2,1};
		this.A.addElement(a);
		int[] b = {2,1,1};
		this.A.addElement(b);
		
	}
	
	public void readM (){
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < this.n; i++){
			for(int j = 0; j < this.m;j++){
				int[] a = new int[3];
				int x = sc.nextInt();
				if (x != 0){
					a[0] = i;
					a[1] = j;
					a[2] = x;
					this.A.addElement(a);
				}
			}
		}
		sc.close();
	}
	
	private boolean validity(int i, int j){
		for(int[] num : this.A){
			if (i == num[0] && j == num[1]){
				return false;
			}
		}
		
		return true;
	}
	
	public void add(int i, int j, int x){
		if (x == 0 || i < 0 || i >= this.n || j < 0 || j >= this.m){
			System.out.println("Out of bounds or 0 value");
			return;
		}
		
		if (validity(i, j)){
			int[] a = new int[3];
			a[0] = i; a[1] = j; a[2] = x;
			this.A.add(a);
		}
		
	}
	
	public void remove(int i, int j){
		for(int[] num : this.A){
			if(i == num[0] && j == num[1]){
				this.A.remove(num);
				return;
			}
		}
	}
	
	public void print(){
		boolean bool = false;
		for(int i = 0; i < this.n; i++){
			for(int j = 0; j < this.m; j++){
				for(int[] num : this.A){
					if(num[0] == i && num[1] == j){
						System.out.print(num[2]+" ");
						bool = true;
					}					
				}
				if(!bool){
					System.out.print("0 ");
				}
				bool = false;
			}
			System.out.println();
		}
	}
}
