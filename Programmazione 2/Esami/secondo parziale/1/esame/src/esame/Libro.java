package esame;

public class Libro extends Monografia {
	public int numPagine;
	@Override
	public int numPagine() {
		// TODO Auto-generated method stub
		return numPagine;
	}
	public Libro(String titolo, String autore, int numPagine) throws MonografiaInvalidaException {
		super(titolo, autore);
		this.numPagine = numPagine;
		if(autore==null || titolo==null || autore.equalsIgnoreCase("")|| titolo.equalsIgnoreCase("") || numPagine<=0) {
			throw new MonografiaInvalidaException("Parametri non validi");
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+numPagine;
	}
	
	

}
