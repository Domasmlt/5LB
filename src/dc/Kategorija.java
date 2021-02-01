package dc;

import java.util.Objects;

public class Kategorija {
	
	private String pavadinimas, aprasymas;
	
	public String getpavadinimas() {
		return pavadinimas;
	}
	
	public String getaprasymas() {
		return aprasymas;
	}
	
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	
	public void setAprasymas(String aprasymas) {
		this.aprasymas = aprasymas;
	}
	
	 Kategorija(String pav, String aprasas) {
		 this.pavadinimas = pav;
		 this.aprasymas = aprasas;
	}
	 
	@Override
	public String toString() {
		return "Kategorija{"+ "pavadinimas=" + pavadinimas +", aprasas=" + aprasymas + "}";
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + Objects.hashCode(this.pavadinimas);
		return hash;
	}
	@Override
	public boolean equals (Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		final Kategorija other = (Kategorija) obj;
		if(!Objects.equals(this.pavadinimas, other.pavadinimas)) {
			return false;
		}
		return true; 
	}
}
