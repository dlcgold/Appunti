
public class TrenoMetro {
	Passeggero[] passeggeri;

	public TrenoMetro() {
		this.passeggeri = new Passeggero[50];
	}
	
	public boolean salitaPasseggero(String destinazione, boolean inPosessoDiBiglietto) {
		Passeggero passeggero=new Passeggero(destinazione, inPosessoDiBiglietto);
		for(int i=0; i<passeggeri.length;i++) {
			if(passeggeri[i]==null) {
				passeggeri[i]=passeggero;
				return true;
			}
		}
		return false;
	}
	
	public boolean salitaPasseggero(String destinazione) {
		return salitaPasseggero(destinazione,true);
	}
	
	public Passeggero[] discesaPasseggeri(String destinazione) {
		int conta=0;
		for(int i=0; i<passeggeri.length;i++) {
			if(passeggeri[i]!=null && passeggeri[i].getDestinazione().equals(destinazione) && !passeggeri[i].isInPosessoDiBiglietto()) {
				conta++;
			}
		}
		
		Passeggero[] scrocconi = new Passeggero[conta];
		for(int i=0; i<conta;i++) {
			for(int o=0;o<passeggeri.length;o++) {
				if(passeggeri[o]!=null && !passeggeri[o].isInPosessoDiBiglietto()) {
					scrocconi[i]=passeggeri[o];
				}
			}
		}
		
		for (int i=0; i<passeggeri.length;i++) {
			if(passeggeri[i]!=null && passeggeri[i].getDestinazione().equals(destinazione))
				passeggeri[i]=null;
		}
		return scrocconi;
	}
	
	public int numeroPasseggeri() {
		int conta=0;
		for(int i=0; i<passeggeri.length;i++) {
			if(passeggeri[i]!=null) {
				conta++;
			}
		}
		return conta;
	}
}
