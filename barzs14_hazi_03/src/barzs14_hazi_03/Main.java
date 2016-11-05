package barzs14_hazi_03;

public class Main {

	public static void main(String[] args) {
		Gerinces k = new Emlos();
		System.out.println(k);
		k.halad();
		k = new Kutya();
		System.out.println(k);
		k.halad();
		((Kutya) k).harap();
		k = new Macska();
		System.out.println(k);
		k.halad();
		k = new Hullo();
		System.out.println(k);
		k.halad();
		k = new Madar();
		System.out.println(k);
		k.halad();
	}
}