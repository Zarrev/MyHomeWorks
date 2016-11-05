package barzs14_hazi_03;

public abstract class Gerinces {

	protected int csigSzam;
	protected int labSzam;
	protected String nev = "";

	public abstract void halad();

	@Override
	public String toString() {
		return " Csigolya szám=" + csigSzam + ", Lábak száma=" + labSzam + ", Név="
				+ nev + "]";
	}
		
}
