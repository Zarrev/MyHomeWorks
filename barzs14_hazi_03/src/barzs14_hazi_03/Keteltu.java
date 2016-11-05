package barzs14_hazi_03;

public class Keteltu extends Gerinces{

	private boolean frEszi;
	
	public Keteltu() {
		this(true,4,12,"Béka");
	}
	
	public Keteltu(boolean frEszi, int labSzam, int csigSzam, String nev) {
		super();
		this.frEszi = frEszi;
		this.labSzam = labSzam;
		this.csigSzam = csigSzam;
		this.nev = nev;
	}

	public void halad(){
		System.out.println("A kétéltű úszik és megy.");
	}

	@Override
	public String toString() {
		if (frEszi)
			return "Keteltu [Franciák megeszik, " + super.toString(); 
		else
			return "Keteltu [Franciák nem eszik meg, " + super.toString();
	}

	
	
}
