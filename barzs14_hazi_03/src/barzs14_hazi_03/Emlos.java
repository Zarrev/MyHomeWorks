package barzs14_hazi_03;

public class Emlos extends Gerinces{

	public int fogSzam;
	
	public Emlos() {
		this(32,4,12,"Disznó");
	}
	
	public Emlos(int fogSzam, int labSzam, int csigSzam, String nev) {
		super();
		this.fogSzam = fogSzam;
	}

	public void halad(){
		System.out.println("Az emlős mászik.");
	}

	@Override
	public String toString() {
		return "Emlos [Fogak száma=" + fogSzam + super.toString();
	}
	
}
