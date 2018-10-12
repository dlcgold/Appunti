
public class Passeggero {
	private String destinazione;
	private boolean inPosessoDiBiglietto;
	
	public String getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	public boolean isInPosessoDiBiglietto() {
		return inPosessoDiBiglietto;
	}
	
	public Passeggero(String destinazione, boolean inPosessoDiBiglietto) {
		super();
		if (destinazione==null)
			this.destinazione="CAPOLINEA";
		else
			this.destinazione = destinazione;
		this.inPosessoDiBiglietto = inPosessoDiBiglietto;
	}
	
	public Passeggero(String destinazione) {
		this(destinazione,true);
	}
	
	@Override
	public String toString() {
		return "passeggero [destinazione=" + destinazione + ", inPosessoDiBiglietto=" + inPosessoDiBiglietto + "]";
	}
	
}
