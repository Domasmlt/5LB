package dc;


public class Pajamos extends PiniguJudejimas {
	
	Pajamos(double suma, String data, String pavadinimas, String kategorija, String komentaras){
		super(suma, data, pavadinimas, kategorija, komentaras);
	}
	
	
	@Override
	public String toString() {
		return "Pajamos " + "{"+ "suma = " + suma +", data = " + data + " , pavadinimas = " + pavadinimas + ", kategorija = " + kategorija + ", komentaras = " + komentaras + "}";
	}
	
	public double getsuma() {
		return suma;
	}
	
}
