import java.util.ArrayList;
import java.util.Collection;

public class DistributoreAutomatico {
	private ArrayList<Prodotto> prodotti;

	public DistributoreAutomatico(Collection<Prodotto> prodottiIniziali) {
		super();
		this.prodotti = new ArrayList<Prodotto>();
		prodotti.addAll(prodottiIniziali);
	}
	
	public Prodotto getProdotto(int pos) throws PosizioneNonValidaException {
		if (pos>=this.prodotti.size())
			throw new PosizioneNonValidaException("NOPE");
		return this.prodotti.get(pos);
	}
	
	public void incrementaQuantitaProdotto(int pos, int qt) throws PosizioneNonValidaException {
		if (pos>=this.prodotti.size())
			throw new PosizioneNonValidaException("NOPE");
		Prodotto prodotto=prodotti.get(pos);
		prodotto.incrementaQuantita(qt);
	}
	
	public int acquista(int pos, int valore) throws PosizioneNonValidaException, ProdottoNonAcquistabileException {
		Prodotto prodotto=getProdotto(pos);
		if(prodotto.getQuantita()==0)
			throw new ProdottoNonAcquistabileException("quantita insufficiente");
		if(valore<prodotto.getPrezzoUnitario())
			throw new ProdottoNonAcquistabileException("valore insufficiente");
		prodotto.decrementaQuantita(1);
		return valore-prodotto.getPrezzoUnitario();
		
		
		
	}
	
	
}
