/** Rappresenta un bambino */
public class Bambino extends Persona {
	/** Il peso del bambino alla nascita */
	private double pesoAllaNascita;
	
	/** La lunghezza del bambino */
	private double lunghezza;
	
	/** L'indice Apgar che indica lo stato di adattamento del bambino alla vita */
	private int indiceApgar;
	
	/** Il peso attuale del bambino */
	private double pesoAttuale;
		
	/**
	 * Crea un nuovo bambino con un braccialetto, il peso alla nascita la lunghezza e il suo indice di Apgar
	 * Lancia eccezione se il braccialetto è null, il peso alla nascita e la lunghezza sono minori od ugiali a zero e 
	 * se l'indice di Apgar non e' nel range di valori ammissibili 
	 * */
	public Bambino (Braccialetto braccialetto, double pesoAllaNascita, double lunghezza, int indiceApgar) 
			throws OperazioneNonConsentitaException {
		super(braccialetto);
		if ((indiceApgar < 0 || indiceApgar > 10) || pesoAllaNascita <= 0 || lunghezza <=0)  
			throw new OperazioneNonConsentitaException();
		else {
			this.pesoAllaNascita = pesoAllaNascita;
			this.lunghezza = lunghezza;
			this.indiceApgar = indiceApgar;
			pesoAttuale = pesoAllaNascita;
		}
	}

	/**
	 * Incrementa del valore passato in ingresso il peso del bambino
	 * @param quantitaLatte La quantità di latte 
	 * */
	public void incrementaPeso(double quantitaLatte){
		this.pesoAttuale += quantitaLatte;
	}
	
	/**
	 * Valuta se il bambino è dimettibile effettuando un controllo sui parametri del bambino
	 * @return true se il bambino è dimettibile, false in caso contrario
	 * */
	public boolean dimettibile() {
		boolean ret = true;
		if (indiceApgar < 7) {
				ret = false;
		}
		return ret;
	}
	
	
	@Override
	public String toString() {
		return "pesoAllaNascita=" + pesoAllaNascita + ", lunghezza="
				+ lunghezza + ", indiceApgar=" + indiceApgar
				+ ", pesoDopoAllattamento=" + pesoAttuale
				+ ", " + super.toString();
	}
	
	
	/**
	 * Restituisce l'indice di Apgar del bambino
	 * @return L'indice di Apgar
	 * */
	public int getIndiceApgar(){
		return indiceApgar;
	}
	
	/**
	 * Restituisce il peso attuale del bambino
	 * @return Il peso attuale
	 * */
	public double getPesoAttuale(){
		return pesoAttuale;
	}
	
	/**
	 * Restituisce il peso alla nascita del bambino
	 * @return Il peso alla nascita
	 * */
	public double getPesoAllaNascita(){
		return pesoAllaNascita;
	}
	
	/**
	 * Restituisce la lunghezza del bambino
	 * @return La lunghezza
	 * */
	public double getLunghezza(){
		return lunghezza;
	}
}
