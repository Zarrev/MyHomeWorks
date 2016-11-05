package barzs14_hazi_03;

public class Hal extends Gerinces {

	{
		labSzam = 0;
	}
	
	private int uszonySzam;
	
	public Hal() {
		this(7, 12, "Ponty");
	}
	
	public Hal(int uszonySzam, int csigSzam, String nev) {
		super();
		this.uszonySzam = uszonySzam;
		this.nev = nev;
		this.csigSzam = csigSzam;
	}

	public void halad(){
		System.out.println("A hal úszik.");
	}

	@Override
	public String toString() {
		return "Hal [Uszony(ok) száma=" + uszonySzam + super.toString();
	}
	
	
	
}
