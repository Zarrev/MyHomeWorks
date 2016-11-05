package barzs14_hazi_03;

public final class Kutya extends Emlos {

	private boolean ugate;
	
	public Kutya() {
		this(true, 32,4,12,"Bodri");
	}

	public Kutya(boolean ugate, int fogSzam, int labSzam, int csigSzam, String nev) {
		super(fogSzam, labSzam, csigSzam, nev);
		this.ugate = ugate;
	}

	public void halad(){
		System.out.println("A kutya farokcsóválva szalad.");
	}
	
	@Override
	public String toString() {
		if(ugate)
			return "Kutya [Ez a kutya ugat, " + super.toString();
		else
			return "Kutya [Ez a kutya nem ugat, " + super.toString();
	}
	
	public void harap(){
		if (ugate)
			System.out.println("A kutya harap");
		else
			System.out.println("A kutya nem harap");
	}
	
}
