package esame;

public abstract  class Monografia {
	public String titolo;
	public String autore;
	
	public String geTitolo() {
		return titolo;
	}
	public String getAutore() {
		return autore;
	}
	
	public abstract int numPagine();
	
	public Monografia(String titolo, String autore) throws MonografiaInvalidaException{
		this.titolo = titolo;
		this.autore = autore;
		if(autore==null || titolo==null || autore.equalsIgnoreCase("")|| titolo.equalsIgnoreCase("")) {
			throw new MonografiaInvalidaException("Stringa non valida");
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Monografia other = (Monografia) obj;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Monografia [titolo=" + titolo + ", autore=" + autore + "]";
	}
}
