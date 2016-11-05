package barzs14_hazi_03;

public class Hullo extends Gerinces{

	private boolean mergezo;
	
	public Hullo() {
		this(true, 0, 12, "Kígyó");
	}
	
	public Hullo(boolean mergezo, int labSzam, int csigSzam, String nev) {
		super();
		this.mergezo = mergezo;
		this.labSzam = labSzam;
		this.csigSzam = csigSzam;
		this.nev = nev;
	}

	public void halad(){
		System.out.println("A hüllő csendesen siklik.");
	}

	@Override
	public String toString() {
		if(this.mergezo)
			return "Hullo [Ez az állat mérgezo, " + super.toString();
		else
			return "Hullo [Ez az állat nem mérgezo, " + super.toString();
	}
	
	

}
