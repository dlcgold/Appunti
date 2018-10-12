/**
 * Il braccialetto che identifica in maniera univoca il bambino e la madre 
 * */
public class Braccialetto {
	/** Il codice */
	private int codice;
	
	/** Crea un nuovo braccialetto */
	public Braccialetto(int codice){
		this.codice = codice;
	}
	
	/** Restituisce il codice del braccialetto */
	public int getCodice(){
		return codice;
	}
	
	/**
	 * Verifica che il braccialetto corrente è uguale a quello passato in ingresso
	 * */
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null) return false;
		if(!(o instanceof Braccialetto)) return false;
		Braccialetto b = (Braccialetto) o;
		if(codice == b.codice) return true;
		return false;
	}

	/** Restituisce lo stato del praccialetto in formato stringa
	 * @return Lo stato del braccialetto */
	public String toString() {
		return "braccialetto con codice " + codice;
	} 
}
