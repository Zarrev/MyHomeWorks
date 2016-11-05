package barzs14_hazi_03;

public class Madar extends Gerinces{

	private int szarnySzam;
	
	public Madar() {
		this(2,2,12,"Turulmadár");
	}
	
	public Madar(int szarnySzam, int labSzam, int csigSzam, String nev) {
		super();
		this.szarnySzam = szarnySzam;
		this.labSzam = labSzam;
		this.csigSzam = csigSzam;
		this.nev = nev;
	}

	public void halad(){
		System.out.println("A madár repül.");
	}

	@Override
	public String toString() {
		return "Madar [Szárnyak száma=" + szarnySzam + super.toString();
	}
	
	

}
