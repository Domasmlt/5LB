package dc;


public class Islaidos extends PiniguJudejimas{
	
	Islaidos(double suma, String data, String pavadinimas, String kategorija, String komentaras, String numeris){
		super(suma, data, pavadinimas, kategorija, komentaras, numeris);
	}
	
	@Override
	public String toString() {
		return  "Islaidos " + "{"+ "suma = " + suma + ", data = " + data + " , pavadinimas = " + pavadinimas + ", kategorija = " + kategorija + ", komentaras = " + komentaras + ", numeris = " + cekionumeris + "}";
	}
	
	public double getsuma() {
		return -suma;
	}

}
