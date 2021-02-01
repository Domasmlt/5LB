package dc;


public class PiniguJudejimas {
	protected double suma;
	protected String data;
	protected String pavadinimas;
	protected String kategorija;
	protected String komentaras;
	protected String cekionumeris;

	PiniguJudejimas(double suma, String data, String pavadinimas, String kategorija, String komentaras) {
		this.suma = suma;
		this.data = data;
		this.pavadinimas = pavadinimas;
		this.kategorija = kategorija;
		this.komentaras = komentaras;
	}

	PiniguJudejimas(double suma, String data, String pavadinimas, String kategorija, String komentaras, String numeris) {
		this.suma = suma;
		this.data = data;
		this.pavadinimas = pavadinimas;
		this.kategorija = kategorija;
		this.komentaras = komentaras;
		this.cekionumeris = numeris;
	}

	public String getkategorija() {
		return kategorija;
	}
	
	public String getdate() {
		return data;
	}
        
        public String getpavadinimas() {
		return pavadinimas;
	}
        
        public String getkomentaras() {
		return komentaras;
	}
        
        public String getnumeris() {
		return cekionumeris;
	}
	

}
