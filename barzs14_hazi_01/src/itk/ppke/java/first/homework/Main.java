package itk.ppke.java.first.homework;

import java.util.Scanner;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	//valamier nem mukodik a sok scanner miatt. erdekes, amikor a false agban az sc.close()-t az
	//egesz vegere tettem es kivettem a wich fgv-t es egy boolean-nel szorakoztam akkor ment
	public static boolean which(){
		
		String c = sc.next();
		if (c.equals("y")){
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("A pelda matrixot (y) vagy sajat matrixot hasznalnal (barmi)?");
		if(!which()){
			SparseMatrix A = new SparseMatrix();
			System.out.println("Matrix:");
			A.print();
			System.out.println();
			System.out.println("A matrix (2,2) helyere berakjuk a 6-ot (ha lehet) es az (2,3)-re az 1-et:");
			A.add(1, 1, 6);
			A.add(1, 2, 1);
			A.print();
			System.out.println();
			System.out.println("Toroljuk a (3,2) helyen levo elemet: ");
			A.remove(2, 1);
			A.print();
		}
		else{
			System.out.println("Adja meg hanyszor hanyas legyen a matrix!");
			System.out.println("col: ");
			int col = sc.nextInt();
			System.out.println("row: ");
			int row = sc.nextInt();
			SparseMatrix A = new SparseMatrix(col,row);
			A.print();			
			System.out.println();
			System.out.println("A matrix (2,2) helyere berakjuk a 6-ot (ha lehet) es az (2,3)-re az 1-et:");
			A.add(1, 1, 6);
			A.add(1, 2, 1);
			A.print();
			System.out.println();
			System.out.println("Toroljuk a (3,2) helyen levo elemet: ");
			A.remove(2, 1);
			A.print();
		}
		sc.close();
	}

}
