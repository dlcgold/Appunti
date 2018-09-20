package esame;

import java.util.HashSet;


public class Serie extends Monografia {
	
	HashSet<Monografia> monografie= new HashSet<Monografia>();
	//public Monografia[] monografie;
	
	public Serie(Monografia[] monografie, String titolo, String autore) throws MonografiaInvalidaException {
		super(titolo, autore);
		if( monografie==null) {
			throw new MonografiaInvalidaException("Parametri non validi");
		}
	     for(int i=0;i<monografie.length;i++) {
	    	this.monografie.add(monografie[i]);
	    	if(monografie[i].autore!=autore)
	    		throw new MonografiaInvalidaException("Parametri non validi");
	    	
	     }
	}

	public void aggiungiMonografia(Monografia monografia) throws MonografiaInvalidaException  {
		if(monografia.getAutore().equalsIgnoreCase(this.autore))
			monografie.add(monografia);
		else
			throw new MonografiaInvalidaException("Parametri non validi");

	}
	@Override
	public int numPagine() {
		int conta=0;
		for(Monografia i: monografie) {
			conta+=i.numPagine();
		}
		return conta;
	}
	
	public int getNumMonografie() {
		return monografie.size();
	}
	
	public int contaLibriNonSerie() {
		int conta=0;
		for(Monografia i: monografie) {
			if(i instanceof Libro )
				conta++;
		}
		return conta;
	}

	@Override
	public String toString() {
		String output="";
		for(Monografia i: monografie) {
			output+=monografie.toString()+" ";
		}
		output+= "Serie [monografie=" + monografie + ", titolo=" + titolo + ", autore=" + autore + "]";
		return output;
	}

	
	
}
