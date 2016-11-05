package barzs14_hazi_03;

public final class Macska extends Emlos{

	private String szorSzin;
	
	public Macska() {
		this("fehér", 32,4,12,"Mici");
	}

	public Macska(String szorSzin, int fogSzam, int labSzam, int csigSzam, String nev) {
		super(fogSzam, labSzam, csigSzam, nev);
		this.szorSzin = szorSzin;
	}
	
	public void halad(){
		System.out.println("A macska sündörög.");
	}
	
	@Override
	public String toString() {
		return "Macska [Szörzet színe="+ szorSzin + super.toString();
	}
}
