
public abstract class Prodotto {
	private int quantita;

	public Prodotto(int quantita) {
		super();
		if(quantita<0)
			quantita=0;
		this.quantita = quantita;
	}

	public int getQuantita() {
		return quantita;
	}
	
	public abstract int getPrezzoUnitario();
	
	public void incrementaQuantita(int quantita) {
		this.quantita+=quantita;
	}
	
	public void decrementaQuantita(int quantita) {
		this.quantita-=quantita;
	}
	
}
